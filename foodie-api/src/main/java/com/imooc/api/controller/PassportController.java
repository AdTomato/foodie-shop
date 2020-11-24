package com.imooc.api.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.bo.ShopcatBO;
import com.imooc.bo.UserBO;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangyong
 * @time 2020/6/24 1:28
 */
@Api(value = "注册登陆", tags = {"用于注册登陆的相关接口"})
@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController{

    @Autowired
    UserService userService;

    @Resource
    RedisOperator redisOperator;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {

        // 1. 判断参数是否为空
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }

        // 2. 查找注册用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }

        // 3. 请求成功，用户名不存在
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public IMOOCJSONResult register(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();
        // 0. 判断用户名和密码必需不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }
        // 1. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已存在");
        }
        // 2. 密码长度不能少于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能少于6");
        }
        // 3. 判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }

        // 4. 实现注册
        Users userResult = userService.createUser(userBO);
        setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user", JSON.toJSONString(userResult), true);
        synchShopcartData(request, response, userResult.getId());
        return IMOOCJSONResult.ok();

    }

    @ApiOperation(value = "用户登陆", notes = "用户登陆", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        // 0. 判断用户名和密码必需不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        // 1. 实现登陆
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        if (userResult == null) {
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }
        userResult = setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user", JSON.toJSONString(userResult), true);

        // TODO 生成用户token，存入redis会话
        // 同步购物车数据
        synchShopcartData(request, response, userResult.getId());

        return IMOOCJSONResult.ok(userResult);
    }

    public void synchShopcartData(HttpServletRequest request, HttpServletResponse response, String userId){
        // 从redis中获取购物车数据
        Map<Object, Object> shopcartRedis = redisOperator.hgetall(FOODIE_SHOPCART + ":" + userId);
        // 从cookie中获取数据
        String shopcartCookie = CookieUtils.getCookieValue(request, FOODIE_SHOPCART, true);
        // 1. redis中无数据,如果cookie中的购物车为空,那么这个时候不做任何处理
        // 2. redis中无数据,如果cookie中购物车不为空,此时直接放入redis中
        // 3. redis中有数据,如果cookie中购物车为空,此时直接把redis的购物车覆盖到本地cookie
        // 4. redis中有数据,如果cookie中购物车不为空,如果cookie中的某个商品在redis中存在,则以cookie为主,删除redis中的,把cookie中的商品直接覆盖redis中(参考京东)
        if (CollectionUtils.isEmpty(shopcartRedis)) {
            if (StringUtils.isBlank(shopcartCookie)) {
                // redis中无数据，cookie中无数据，不做处理
                return;
            } else {
                // redis中无数据，cookie中有数据，将cookie中的数据同步到redis中
                List<ShopcatBO> shopcatBOList = JSON.parseArray(shopcartCookie, ShopcatBO.class);
                ConcurrentHashMap<Object, Object> data = new ConcurrentHashMap<>(shopcatBOList.size());
                shopcatBOList.forEach(shopcatBO -> {
                    data.put(shopcatBO.getSpecId(), JSON.toJSONString(shopcatBO));
                });
                redisOperator.hmset(FOODIE_SHOPCART + ":" + userId, data);
            }
        } else {
            if (StringUtils.isBlank(shopcartCookie)) {
                // redis中有数据，cookie中无数据，将redis中的数据同步到cookie中
                List<ShopcatBO> shopcatBOList = new ArrayList<>(shopcartRedis.size());
                shopcartRedis.keySet().forEach(specId -> {
                    ShopcatBO bo = JSON.parseObject((String) shopcartRedis.get(specId), ShopcatBO.class);
                    shopcatBOList.add(bo);
                });
                CookieUtils.setCookie(request, response, FOODIE_SHOPCART, JSON.toJSONString(shopcatBOList), true);
            } else {
                // redis中有数据，cookie中有数据
                List<ShopcatBO> shopcatBOList = JSON.parseArray(shopcartCookie, ShopcatBO.class);
                shopcatBOList.forEach(shopcatBO -> {
                    String specId = shopcatBO.getSpecId();
                    if (shopcartRedis.containsKey(specId)) {
                        // redis中已经存在了
                        ShopcatBO bo = JSON.parseObject((String) shopcartRedis.get(specId), ShopcatBO.class);
                        bo.setBuyCounts(shopcatBO.getBuyCounts());
                        shopcartRedis.put(specId, JSON.toJSONString(bo));
                    } else {
                        // redis中不存在的购物数据
                        shopcartRedis.put(specId, JSON.toJSONString(shopcatBO));
                    }
                });
                // 将最新的shopcartRedis存入redis中
                redisOperator.hmset(FOODIE_SHOPCART + ":" + userId, shopcartRedis);
                // 将最新的购物车数据回写到cookie中
                shopcatBOList.clear();
                shopcartRedis.keySet().forEach(specId -> {
                    ShopcatBO bo = JSON.parseObject((String) shopcartRedis.get(specId), ShopcatBO.class);
                    shopcatBOList.add(bo);
                });
                CookieUtils.setCookie(request, response, FOODIE_SHOPCART, JSON.toJSONString(shopcatBOList), true);
            }
        }

    }

    @ApiOperation(value = "用户退出登陆", notes = "用户退出登陆", httpMethod = "POST")
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {

        // 请用用户的相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");

        // 用户退出登陆，需要清空购物车
        CookieUtils.deleteCookie(request, response, FOODIE_SHOPCART);
        // TODO 分布式会话中需要清除用户数据
        return IMOOCJSONResult.ok();
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}
