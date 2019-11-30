package com.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//默认配置
@DefaultProperties(
        groupKey = "helloService",//分组配置
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")//设置超时时间为10s
        },
        threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "30"),//线程池大小
                @HystrixProperty(name = "maxQueueSize", value = "10")//最大等待队列书数
        },
        threadPoolKey = "helloService"//线程池的唯一名称
)
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    //指定fallbackMethod熔断方法
    @HystrixCommand(
            fallbackMethod = "hiError"//后备执行方法
    )
    public String hiService(String name) {
        return restTemplate.getForObject("http://config-client/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}