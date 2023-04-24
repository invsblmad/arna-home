package com.inai.arna.controller;

import com.inai.arna.dto.TokenResponse;
import com.inai.arna.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/protected/token")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;
    @GetMapping("/refresh")
    public TokenResponse refresh() {
        return tokenService.refreshTokens();
    }
}
