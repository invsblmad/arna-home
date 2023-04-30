package com.inai.arna.service.impl;

import com.inai.arna.service.AuthService;
import com.inai.arna.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthService authService;

    @Override
    public Integer getAuthenticatedUserId() {
        var user = authService.getAuthenticatedUser();
        if (user.isPresent())
            return user.get().getId();
        return null;
    }
}
