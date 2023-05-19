package com.inai.arna.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReviewRequest {
    @NotNull(message = "The mark can't be null")
    private BigDecimal mark;
    @NotBlank(message = "The text can't be null or empty")
    private String text;
}
