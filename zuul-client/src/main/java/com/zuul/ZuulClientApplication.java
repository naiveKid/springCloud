package com.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//应用入口
@SpringBootApplication
//开启 zuul
@EnableZuulProxy
//eureka 客户端
@EnableEurekaClient
public class ZuulClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulClientApplication.class, args);
    }
}
