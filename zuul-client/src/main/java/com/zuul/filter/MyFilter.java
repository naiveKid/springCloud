package com.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//服务过滤，实现抽象类ZuulFilter
@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    //四种不同生命周期的过滤器类型
    //pre：路由之前
    //routing：路由之时
    //post： 路由之后
    //error：发送错误调用
    @Override
    public String filterType() {
        return "pre";//路由之前
    }

    //过滤的顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    //根据某些情况判断是否要过滤
    @Override
    public boolean shouldFilter() {
        return true;//此处永远过滤
    }

    //过滤器的具体逻辑
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {//没有传token参数
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);//让zuul过滤该请求,不对其进行路由
            ctx.setResponseStatusCode(401);//设置返回的错误码
            //可以对返回的body进行编辑
            //ctx.setResponseBody(body);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return null;
        }
        //移除token
        request.removeAttribute("token");
        log.info("ok");
        return null;
    }
}