package com.ribbon.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:自定义负载均衡规则
 */
public class NewRuleConfig {//名字不能为RibbonConfiguration.否则扫描之后将和原来类名冲突
    @Autowired
    private IClientConfig iClientConfig;

    //ping规则
    @Bean
    public IPing ribbonPing(IClientConfig iClientConfig){
        return new PingUrl(false,"/actuator/health");//调用健康检测
    }

    //调用规则
    @Bean
    public IRule ribbonRule(IClientConfig iClientConfig){
        return new AvailabilityFilteringRule();//不调用之前调用失败的实例
    }
}