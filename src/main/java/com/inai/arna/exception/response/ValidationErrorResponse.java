package com.inai.arna.exception.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> errors;

    public ValidationErrorResponse(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
