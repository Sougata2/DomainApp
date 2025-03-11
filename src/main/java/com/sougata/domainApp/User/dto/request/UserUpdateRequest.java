package com.sougata.domainApp.User.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequest {
    private UUID id;
    @NotBlank(message = "First Name cannot be empty")
    private String firstName;
    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
