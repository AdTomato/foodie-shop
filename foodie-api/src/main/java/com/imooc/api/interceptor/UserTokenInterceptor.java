package com.imooc.api.interceptor;

import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 描述: 用户信息拦截器
 *
 * @author wangyong
 */
public class UserTokenInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UserTokenInterceptor.class);
    public static final String REDIS_USER_TOKEN = "redis_user_token";
    @Resource
    RedisOperator redisOperator;

    /**
     * 拦截请求，在访问controller调用之前
     *
     * @param request
     * @param response
     * @param handler
     * @return false:请求被拦截，被驳回；true:请求校验通过
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入到拦截器，被拦截");
        String headerUserId = request.getHeader("headerUserId");
        String headerUserToken = request.getHeader("headerUserToken");
        if (StringUtils.isNotBlank(headerUserId) && StringUtils.isNotBlank(headerUserToken)) {
            String uniqueToken = redisOperator.get(REDIS_USER_TOKEN + ":" + headerUserId);
            if (StringUtils.isNotBlank(uniqueToken)) {
                if (!uniqueToken.equals(headerUserToken)) {
                    logger.info("出现异地登录的情况");
                    returnErrorResponse(response, IMOOCJSONResult.errorMsg("出现异地登录的情况"));
                    return false;
                }
            } else {
                logger.info("请登录");
                returnErrorResponse(response, IMOOCJSONResult.errorMsg("请登录"));
                return false;
            }
        } else {
            logger.info("请登录");
            returnErrorResponse(response, IMOOCJSONResult.errorMsg("请登录"));
            return false;
        }
        return true;
    }

    private void returnErrorResponse(HttpServletResponse response, IMOOCJSONResult result) {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 请求访问controller之后，渲染视图之前
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求访问controller之后，渲染视图之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
