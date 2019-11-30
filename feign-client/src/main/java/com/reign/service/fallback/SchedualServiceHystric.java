package com.reign.service.fallback;

import com.reign.service.SchedualService;
import org.springframework.stereotype.Component;

//此处的实现是作为熔断器
@Component
public class SchedualServiceHystric implements SchedualService {
    //对需要实现熔断方法的方法进行重写即可
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry, you are fail,"+name;
    }
}