package com.inai.arna.exception.handler;

import com.inai.arna.exception.*;
import com.inai.arna.exception.response.ErrorResponse;
import com.inai.arna.exception.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handle(UserAlreadyExistsException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(PasswordNotConfirmedException.class)
    public ResponseEntity<ErrorResponse> handle(PasswordNotConfirmedException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(BAD_REQUEST).body(
                        new ValidationErrorResponse("Validation error", errors));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(CloudStorageError.class)
    public ResponseEntity<ErrorResponse> handle(CloudStorageError e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ItemAlreadyLikedException.class)
    public ResponseEntity<ErrorResponse> handle(ItemAlreadyLikedException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(CreditCardExpiredException.class)
    public ResponseEntity<ErrorResponse> handle(CreditCardExpiredException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(CreditCardNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(CreditCardNotValidException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }
}
