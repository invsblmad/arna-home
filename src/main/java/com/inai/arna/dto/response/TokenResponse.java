package com.inai.arna.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
