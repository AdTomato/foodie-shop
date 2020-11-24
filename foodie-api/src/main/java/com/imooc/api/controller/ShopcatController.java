package com.imooc.api.controller;

import com.imooc.bo.ShopcatBO;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Api(value = "购物车接口controller", tags = {"购物车接口相关api"})
@RequestMapping("/shopcart")
@RestController
public class ShopcatController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ShopcatController.class);

    @Resource
    RedisOperator redisOperator;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public IMOOCJSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcatBO shopcatBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        log.info("shopcatBO:{}", shopcatBO);

        // 前端用户在登陆的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        // 需要判断当前购物车中包含已经存在的商品，如果存在则累加购买数量
        Map<Object, Object> shopcartJson = redisOperator.hgetall(FOODIE_SHOPCART + ":" + userId);
        if(!CollectionUtils.isEmpty(shopcartJson)) {
            if (shopcartJson.containsKey(shopcatBO.getSpecId())) {
                ShopcatBO bo = JsonUtils.jsonToPojo(String.valueOf(shopcartJson.get(shopcatBO.getSpecId())), ShopcatBO.class);
                bo.setBuyCounts(bo.getBuyCounts() + shopcatBO.getBuyCounts());
                shopcartJson.put(shopcatBO.getSpecId(), JsonUtils.objectToJson(bo));
            } else {
                shopcartJson.put(shopcatBO.getSpecId(), JsonUtils.objectToJson(shopcatBO));
            }
        } else {
            shopcartJson = new ConcurrentHashMap<>(1);
            shopcartJson.put(shopcatBO.getSpecId(), JsonUtils.objectToJson(shopcatBO));
        }

        // 将新的购物车数据覆盖redis中的购物车数据
        redisOperator.hmset(FOODIE_SHOPCART + ":" + userId, shopcartJson);


        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public IMOOCJSONResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }

        // 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端redis购物车中的商品
        Map<Object, Object> shopcatMap = redisOperator.hgetall(FOODIE_SHOPCART + ":" + userId);
        if (!CollectionUtils.isEmpty(shopcatMap)) {
            if (shopcatMap.containsKey(itemSpecId)) {
                shopcatMap.remove(itemSpecId);
            }
            redisOperator.hmset(FOODIE_SHOPCART + ":" + userId, shopcatMap);
        }
        return IMOOCJSONResult.ok();
    }

}
