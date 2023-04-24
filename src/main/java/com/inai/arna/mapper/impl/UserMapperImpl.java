package com.inai.arna.mapper.impl;

import com.inai.arna.dto.RegistrationRequest;
import com.inai.arna.mapper.UserMapper;
import com.inai.arna.model.user.Role;
import com.inai.arna.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public User toEntity(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(Role.CUSTOMER);
        return user;
    }
}
