package com.example.SC.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    // Configure interceptors for JWT authentication
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // Define a list of paths that should be excluded from JWT authentication
        List<String> excludedPaths = Arrays.asList("/user/login", "/user/register", "/user/test");

        // Add the JwtInterceptor to the interceptor registry, specifying path patterns to include and exclude
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludedPaths);

        // Call the superclass method to ensure default configuration
        super.addInterceptors(registry);
    }

    // Create and configure a JwtInterceptor bean
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
