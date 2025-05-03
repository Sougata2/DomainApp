package com.sougata.domainApp.employee.dto;

import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.shared.MasterDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.sougata.domainApp.employee.entity.EmployeeEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements MasterDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isValid;
    private RoleDto defaultRole;
    private Set<RoleDto> roles;

    @Override
    public String toString() {
        Set<String> roleNames = roles.stream().map(RoleDto::getRoleName).collect(Collectors.toSet());
        return "EmployeeDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isValid=" + isValid +
                ", roles=" + roleNames +
                '}';
    }
}