package com.psq.shiroexercise.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControler {

    @GetMapping("/test")
    @RequiresPermissions("system:user:select")
    public String test() {
        return " this is test";
    }

    @GetMapping("/test2")
    @RequiresPermissions("system:user:add")
    public String test2() {
        return " this is test2";
    }

    @GetMapping("/doLogin")
    public void doLogin() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken());
            System.out.println("登录成功!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败!");
        }
    }
}
