package com.sougata.domainApp.User.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
public class UserErrorResponse {
    private HttpStatus status;
    private String error;
    private String message;
    private LocalDateTime timeStamp;
}
