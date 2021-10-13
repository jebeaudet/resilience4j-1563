/*
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.resilience4.circuitbreaker;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class CircuitBreakerInstance
{
    public static final String CIRCUIT_BREAKER = "circuitBreaker";

    @CircuitBreaker(name = CIRCUIT_BREAKER)
    public <T> T wrapSupplier(Supplier<T> supplier)
    {
        return supplier.get();
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER)
    public void wrap(Runnable runnable)
    {
        runnable.run();
    }
}
