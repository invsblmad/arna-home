package com.inai.arna.service;

import com.inai.arna.dto.AuthenticationRequest;
import com.inai.arna.dto.RegistrationRequest;
import com.inai.arna.dto.TokenResponse;
import com.inai.arna.model.user.User;

public interface AuthService {
    TokenResponse login(AuthenticationRequest authenticationRequest);
    TokenResponse register(RegistrationRequest registrationRequest);
    User getAuthenticatedUser();
}
