package com.inai.arna.service;

import com.inai.arna.dto.request.AuthenticationRequest;
import com.inai.arna.dto.request.RegistrationRequest;
import com.inai.arna.dto.response.TokenResponse;
import com.inai.arna.model.user.User;

import java.util.Optional;

public interface AuthService {
    TokenResponse login(AuthenticationRequest authenticationRequest);
    TokenResponse register(RegistrationRequest registrationRequest);
    Optional<User> getAuthenticatedUser();
}
