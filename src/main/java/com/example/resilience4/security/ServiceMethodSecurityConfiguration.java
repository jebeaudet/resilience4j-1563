/*
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.resilience4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServiceMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration
{
    @Autowired
    private MethodSecurityHandler methodSecurityHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler()
    {
        return methodSecurityHandler;
    }
}
