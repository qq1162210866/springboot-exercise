package com.psq.securityexercise.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControler {

    @GetMapping("/test")
    @PreAuthorize("hasPermission('/select','system:user:select')")
    public String test() {
        return " this is test";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasPermission('/select','system:user:add')")
    public String test2() {
        return " this is test2";
    }
}
