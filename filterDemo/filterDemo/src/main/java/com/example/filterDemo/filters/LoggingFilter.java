package com.example.filterDemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("Incoming request: " + request.getMethod() + " " + request.getRequestURI());
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestId = UUID.randomUUID().toString();
        response.setHeader("X-Request-ID", requestId);
        System.out.println("Request ID: " + requestId);
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("Outgoing response: " + response.getStatus());
            System.out.println("Request processing time: " + (endTime - startTime) + " ms");
        }
    }
}
