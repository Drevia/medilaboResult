package com.openclassrooms.medilaboResult.config;


import com.openclassrooms.medilaboResult.service.JwtService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

@Configuration
public class FeignConfiguration {

    @Autowired
    private JwtService jwtService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer " + getString());
    }

    private String getString() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return jwtService.generateToken(principal);
    }
}
