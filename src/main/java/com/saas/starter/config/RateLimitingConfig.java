package com.saas.starter.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class RateLimitingConfig {

    @Bean
    @Order(1)
    public Filter rateLimitFilter() {
        return new Filter() {
            private final Cache<String, Integer> requestCounts = Caffeine.newBuilder()
                    .expireAfterWrite(1, TimeUnit.MINUTES)
                    .build();

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response;

                String key = req.getRemoteAddr() + ":" + req.getRequestURI();
                Integer count = requestCounts.getIfPresent(key);
                if (count != null && count > 60) {
                    res.setStatus(429);
                    res.setContentType("application/json");
                    res.getWriter().write("{\"error\":\"Rate limit exceeded. Try again later.\"}");
                    return;
                }
                requestCounts.put(key, count != null ? count + 1 : 1);
                chain.doFilter(request, response);
            }
        };
    }
}
