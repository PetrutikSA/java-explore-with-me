package ru.practicum.util;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return ApiError.builder()
                .errors(List.of(methodArgumentNotValidException.getStackTrace()))
                .message(methodArgumentNotValidException.getBody().getDetail())
                .reason(fieldErrors.getFirst().getDefaultMessage())
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(Instant.now().toString())
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValid(ConstraintViolationException constraintViolationException) {
        return ApiError.builder()
                .errors(List.of(constraintViolationException.getStackTrace()))
                .message("Invalid method parameter")
                .reason(constraintViolationException.getLocalizedMessage())
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(Instant.now().toString())
                .build();
    }
}
