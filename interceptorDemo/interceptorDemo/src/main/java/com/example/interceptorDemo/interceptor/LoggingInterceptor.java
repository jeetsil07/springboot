package com.example.interceptorDemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle: Incoming request: " + request.getMethod() + " " +
                request.getRequestURI()+" "+
                request.getQueryString()+" "+
                request.getHeaderNames()+" "+
                request.getRemoteAddr()+" "+
                request.getRemoteHost()+" "+
                request.getRemotePort());
        if (handler instanceof HandlerMethod method) {
            System.out.println("method name: " + method.getMethod().getName());
            System.out.println("controller name: " + method.getBeanType().getSimpleName());
        }
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle: Request completed: " + request.getMethod() + " " + request.getRequestURI());
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion: Request processing completed: " + request.getMethod() + " " + request.getRequestURI());
        if (ex != null) {
            System.out.println("Exception occurred: " + ex.getMessage());
        }else {
            System.out.println("No exception occurred during request processing.");
        }
    }
}
