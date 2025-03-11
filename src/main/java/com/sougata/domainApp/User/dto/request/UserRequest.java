package com.sougata.domainApp.User.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "firstName cannot be empty")
    private String firstName;
    @NotBlank(message = "lastName cannot be empty")
    private String lastName;
    @NotBlank(message = "email cannot be empty")
    private String email;
    @NotBlank(message = "password cannot be empty")
    private String password;
}
