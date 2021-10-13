/*
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.resilience4.security;

import java.util.Objects;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.resilience4.circuitbreaker.CircuitBreakerInstance;

@Component
public class MethodSecurityHandler extends DefaultMethodSecurityExpressionHandler
{
    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    private CircuitBreakerInstance circuitBreakerInstance;

    public MethodSecurityHandler(CircuitBreakerInstance circuitBreakerInstance)
    {
        this.circuitBreakerInstance = Objects.requireNonNull(circuitBreakerInstance);
    }

    @Override
    public void setTrustResolver(AuthenticationTrustResolver trustResolver)
    {
        super.setTrustResolver(trustResolver);
        this.trustResolver = trustResolver;
    }

    @Override
    public MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
                                                                           MethodInvocation invocation)
    {
        var root = new MethodSecurityRoot(authentication, circuitBreakerInstance);
        root.setThis(invocation.getThis());
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
