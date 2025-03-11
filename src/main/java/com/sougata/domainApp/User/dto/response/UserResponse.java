package com.sougata.domainApp.User.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
