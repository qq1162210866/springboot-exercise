package com.psq.securityexercise.config;

import com.psq.securityexercise.dto.UserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccessControlFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        UserInfo user = new UserInfo();
        user.setUserId(123456);
        List<GrantedAuthority> permissionList = new ArrayList<>();
        permissionList.add((GrantedAuthority) () -> "system:user:select");
        permissionList.add((GrantedAuthority) () -> "system:user:delete");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, permissionList);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }
}
