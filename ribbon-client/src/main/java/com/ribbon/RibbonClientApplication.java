package com.ribbon;

import com.ribbon.config.NewRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//应用入口
@SpringBootApplication
//eureka 客户端
@EnableEurekaClient
//开启熔断器，通过@HystrixCommand调用服务，实现fallback方法
@EnableHystrix
//开启熔断器监控
@EnableHystrixDashboard
//需要自定义均衡负载的规则,才配置RibbonClient.
@RibbonClient(name="config-client",configuration = NewRuleConfig.class)
public class RibbonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonClientApplication.class, args);
    }

    @Bean
    //让这个RestTemplate在请求时拥有客户端负载均衡的能力
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}