package com.inai.arna.service.impl;

import com.inai.arna.dto.request.AuthenticationRequest;
import com.inai.arna.dto.request.RegistrationRequest;
import com.inai.arna.dto.response.TokenResponse;
import com.inai.arna.exception.PasswordNotConfirmedException;
import com.inai.arna.exception.UserAlreadyExistsException;
import com.inai.arna.mapper.UserMapper;
import com.inai.arna.model.user.User;
import com.inai.arna.repository.UserRepository;
import com.inai.arna.security.UserDetailsImpl;
import com.inai.arna.service.AuthService;
import com.inai.arna.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public TokenResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenService.generateTokens((UserDetailsImpl) authentication.getPrincipal());
    }

    @Override
    public TokenResponse register(RegistrationRequest registrationRequest) {
        validateEmail(registrationRequest.getEmail());
        validatePasswordConfirmation(
                registrationRequest.getPassword(),
                registrationRequest.getPasswordConfirmation());

        User user = userMapper.toEntity(registrationRequest);
        User savedUser = userRepository.save(user);
        return tokenService.generateTokens(new UserDetailsImpl(savedUser));

    }

    @Override
    public User getAuthenticatedUser() {
        var userDetails = tokenService.getUserDetailsFromToken();
        if (userDetails != null)
            return userDetails.user();
        return null;
    }

    private void validateEmail(String email) {
        if (isEmailNotUnique(email))
            throw new UserAlreadyExistsException("The user with such email already exists");
    }

    private void validatePasswordConfirmation(String password, String confirmation) {
        if (!isPasswordConfirmed(password, confirmation))
            throw new PasswordNotConfirmedException("The password hasn't been confirmed");
    }

    private boolean isEmailNotUnique(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean isPasswordConfirmed(String password, String confirmation) {
        return password.equals(confirmation);
    }

}
