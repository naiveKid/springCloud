package com.reign.service;

import com.reign.config.FeignConfig;
import com.reign.service.fallback.SchedualServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//FeignClient 默认使用轮询的负载均衡规则访问
//value 服务名不能有下跨线
//fallback 指定了熔断的实现类
@FeignClient(value = "config-client", fallback = SchedualServiceHystric.class, configuration = FeignConfig.class)
public interface SchedualService {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}