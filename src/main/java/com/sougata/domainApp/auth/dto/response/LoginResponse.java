package com.sougata.domainApp.auth.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String email;
    private String token;
    private long expiresIn;
}
