package com.sougata.domainApp.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class UserRoleResponse {
    private UUID id;
    private String firstName;
    private String roleName;
}
