package com.sougata.domainApp.role.dto;

import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;
import com.sougata.domainApp.shared.MasterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link com.sougata.domainApp.role.entity.RoleEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements MasterDto {
    private Long id;
    private String roleName;
    private Integer isValid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<EmployeeRoleMapDto> roleMappings;

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", isValid=" + isValid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", roleMappings=" + roleMappings +
                '}';
    }
}