package com.sougata.domainApp.User.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class UpdateUserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime updatedAt;
}
