package com.sougata.domainApp.employeeRoleMap.dto;

import com.sougata.domainApp.employee.dto.EmployeeDto;
import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.role.entity.RoleEntity;
import com.sougata.domainApp.shared.MasterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link com.sougata.domainApp.employeeRoleMap.entity.EmployeeRoleMapEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRoleMapDto implements MasterDto {
    private Long Id;
    private EmployeeDto employee;
    private RoleDto role;
    private Integer isDefault;
    private Integer isValid;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "EmployeeRoleMapDto{" +
                "Id=" + Id +
                ", employee=" + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName() +
                ", role=" + role.getRoleName() +
                ", isDefault=" + isDefault +
                ", isValid=" + isValid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}