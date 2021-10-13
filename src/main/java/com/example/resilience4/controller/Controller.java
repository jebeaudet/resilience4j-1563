/*
 * Copyright (c) Coveo Solutions Inc.
 */
package com.example.resilience4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{
    @GetMapping("/allowed")
    @PreAuthorize("hasPrivilege('true')")
    public String allowed()
    {
        return "allowed";
    }

    @GetMapping("/denied")
    @PreAuthorize("hasPrivilege('false')")
    public String denied()
    {
        return "denied";
    }
}
