package com.sougata.domainApp.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "First Name cannot be empty")
    private String firstName;
    @NotBlank(message = "LastName cannot be empty")
    private String lastName;
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
