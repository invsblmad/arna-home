package com.inai.arna.mapper;

import com.inai.arna.dto.request.RegistrationRequest;
import com.inai.arna.model.user.User;

public interface UserMapper {
    User toEntity(RegistrationRequest registrationRequest);
}
