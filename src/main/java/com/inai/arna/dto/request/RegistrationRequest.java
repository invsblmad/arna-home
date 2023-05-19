package com.inai.arna.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotBlank(message = "The first name can't be null or empty")
    private String firstName;
    @NotBlank(message = "The last name can't be null or empty")
    private String lastName;
    @NotBlank(message = "The email can't be null or empty")
    private String email;
    @NotBlank(message = "The password can't be null or empty")
    @Size(min = 5, message = "The password must be at least 5 characters long")
    private String password;
    private String passwordConfirmation;
}
