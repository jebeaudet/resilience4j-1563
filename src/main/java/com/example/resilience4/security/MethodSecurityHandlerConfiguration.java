/*
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.resilience4.security;

import org.springframework.context.annotation.Bean;

import com.example.resilience4.circuitbreaker.CircuitBreakerInstance;

//@Configuration
public class MethodSecurityHandlerConfiguration
{
    @Bean
    public MethodSecurityHandler methodSecurityHandler(CircuitBreakerInstance circuitBreakerInstance)
    {
        return new MethodSecurityHandler(circuitBreakerInstance);
    }
}
