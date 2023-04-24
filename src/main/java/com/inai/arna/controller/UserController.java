package com.inai.arna.controller;

import com.inai.arna.security.UserDetailsImpl;
import com.inai.arna.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/protected/users")
@RequiredArgsConstructor
public class UserController {
    private final TokenService tokenService;
    @GetMapping
    public String hello() {
        return "hello!";
    }
}
