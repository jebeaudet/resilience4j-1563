/*
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.resilience4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

    @Bean
    public UserDetailsService userDetailsService() throws Exception
    {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                               .username("user")
                               .password("password")
                               .roles("USER")
                               .build());
        return manager;
    }
}
