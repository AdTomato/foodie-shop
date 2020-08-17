package com.imooc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("set")
    public Object set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "OK";
    }

    @GetMapping("get")
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("delete")
    public Object delete(String key) {
        redisTemplate.delete(key);
        return  "OK";
    }

}
