package com.inai.arna.service;

import com.inai.arna.dto.response.TokenResponse;
import com.inai.arna.security.UserDetailsImpl;

public interface TokenService {
    TokenResponse generateTokens(UserDetailsImpl userDetails);
    TokenResponse refreshTokens();
    UserDetailsImpl getUserDetailsFromToken();
}
