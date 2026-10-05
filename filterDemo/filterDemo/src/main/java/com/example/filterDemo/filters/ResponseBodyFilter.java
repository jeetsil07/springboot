package com.example.filterDemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
public class ResponseBodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
        filterChain.doFilter(servletRequest, responseWrapper);
        byte[] originalBodyBytes = responseWrapper.getContentAsByteArray();
        String originalBody = new String(originalBodyBytes);
        String modifiedBody =
               """
                {
                    "originalResponse": "%s",
                    "appName": "Student Management System",
                }
                """.formatted(originalBody);
        responseWrapper.resetBuffer();
        responseWrapper.getWriter().write(modifiedBody);
        responseWrapper.copyBodyToResponse();
    }
}
