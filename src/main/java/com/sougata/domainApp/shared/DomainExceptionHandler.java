package com.sougata.domainApp.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class DomainExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> exceptionHandler(Exception exception) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("timeStamp", LocalDateTime.now());
        response.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
