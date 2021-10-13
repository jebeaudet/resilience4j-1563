/*
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.resilience4.security;

import java.util.Objects;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import com.example.resilience4.circuitbreaker.CircuitBreakerInstance;

public class MethodSecurityRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations
{
    private Object filterObject;
    private Object returnObject;
    private Object target;

    private CircuitBreakerInstance circuitBreakerInstance;

    public MethodSecurityRoot(Authentication authentication, CircuitBreakerInstance circuitBreakerInstance)
    {
        super(authentication);
        this.circuitBreakerInstance = Objects.requireNonNull(circuitBreakerInstance);
    }

    public boolean hasPrivilege(String booleanAsString)
    {
        return circuitBreakerInstance.wrapSupplier(() -> Boolean.valueOf(booleanAsString));
    }

    @Override
    public void setFilterObject(Object filterObject)
    {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject()
    {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject)
    {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject()
    {
        return returnObject;
    }

    @Override
    public Object getThis()
    {
        return target;
    }

    public void setThis(Object target)
    {
        this.target = target;
    }
}
