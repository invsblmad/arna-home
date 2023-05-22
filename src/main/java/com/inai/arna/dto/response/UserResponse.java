package com.inai.arna.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private int id;
    private String imageUrl;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cardNumber;
}
