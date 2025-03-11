package com.sougata.domainApp.User.domain.errors;

import com.sougata.domainApp.User.dto.response.UserErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(UserErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .error("User Not Found")
                .message(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserMergeException.class)
    public ResponseEntity<UserErrorResponse> handleUserMergeException(UserMergeException exception) {
        return new ResponseEntity<>(
                UserErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .error("Parse error")
                        .message(exception.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build()
                , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<UserErrorResponse> handleUserValidationException(ConstraintViolationException exception) {

        return new ResponseEntity<>(
                UserErrorResponse.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .error("Validation Error")
                        .message(exception.getMessage()) // ✅ Return readable validation messages
                        .timeStamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Error");

        // Extract field-specific error messages
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
