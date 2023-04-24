package com.inai.arna.mapper;

import com.inai.arna.dto.RegistrationRequest;
import com.inai.arna.model.user.User;

public interface UserMapper {
    User toEntity(RegistrationRequest registrationRequest);
}
