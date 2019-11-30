package com.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * 测试配置
 */
//允许动态刷新配置
@RefreshScope
@RestController
public class TestController {
    @Value("${test}")
    private String test;

    @RequestMapping("/value")
    public String value() {
        return this.test;
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String hi(String name){
        return "name:"+name+" port:"+port;
    }
}