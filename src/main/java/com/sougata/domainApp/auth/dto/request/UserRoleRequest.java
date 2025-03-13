package com.sougata.domainApp.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class UserRoleRequest {
    private UUID id;
    private String role;
}
