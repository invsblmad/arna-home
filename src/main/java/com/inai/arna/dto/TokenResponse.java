package com.inai.arna.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
