package com.sougata.domainApp.role.dto;

import com.sougata.domainApp.employee.dto.EmployeeDto;
import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.shared.MasterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Set<EmployeeDto> employees;

    @Override
    public String toString() {
        Set<String> employeesNames = employees.stream().map(EmployeeDto::getEmail).collect(Collectors.toSet());
        return "RoleDto{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", isValid=" + isValid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", employees=" + employeesNames +
                '}';
    }
}