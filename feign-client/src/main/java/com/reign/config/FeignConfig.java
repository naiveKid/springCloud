package com.reign.config;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign配置项
 */
@Configuration
public class FeignConfig {//名字不能为FeiConfiguration.否则扫描之后将和原来类名冲突

    /**
     * 若使用feign.Contract 代替SpringMvcContract,
     * 在调用接口时，必须使用@RequestLine 代替 @RequestMapping、@GetMapping、@PutMapping
     * @return
     */
    //@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * 访问eureka的用户名和密码
     *
     * @return
     */
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("hxz", "hxz");
    }
}