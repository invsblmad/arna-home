package com.inai.arna.controller;

import com.inai.arna.dto.AuthenticationRequest;
import com.inai.arna.dto.RegistrationRequest;
import com.inai.arna.dto.TokenResponse;
import com.inai.arna.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthenticationRequest authenticationRequest) {
        return authService.login(authenticationRequest);
    }

    @PostMapping("/register")
    public TokenResponse register(@RequestBody RegistrationRequest registrationRequest) {
        return authService.register(registrationRequest);
    }
}
