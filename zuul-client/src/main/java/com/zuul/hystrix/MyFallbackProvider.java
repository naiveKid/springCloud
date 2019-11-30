package com.zuul.hystrix;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 路由熔断
 */
@Component
public class MyFallbackProvider implements FallbackProvider {
    //指定熔断器功能应用于哪些路由的服务
    //如果需要所有的路由服务都加熔断功能,则返回"*";
    @Override
    public String getRoute() {
        return "feign-client";//此处，只对feign-client增加熔断
    }

    //熔断时执行的逻辑方法
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        System.out.println("route:" + route);
        System.out.println("exception:" + cause.getMessage());
        return new ClientHttpResponse() {
            //返回的状态码
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            //状态码说明
            @Override
            public String getStatusText() throws IOException {
                return "ok";
            }

            @Override
            public void close() {
            }

            //响应主体
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("Sorry, the service is unavailable now.".getBytes());
            }

            //响应头
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}