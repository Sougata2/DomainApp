package com.sougata.domainApp.shared;

import com.sougata.domainApp.employee.dto.EmployeeDto;
import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;
import com.sougata.domainApp.employeeRoleMap.entity.EmployeeRoleMapEntity;
import com.sougata.domainApp.employeeRoleMap.mapper.EmployeeRoleMapMapping;
import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.role.entity.RoleEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Component
public class EntityDtoMapping {
    private final Map<Class<? extends MasterEntity>, Class<? extends MasterDto>> entityDtoMap;
    private final Map<Class<? extends MasterDto>, Class<? extends MasterEntity>> dtoEntityMap;

    public EntityDtoMapping() {
        entityDtoMap = Map.ofEntries(
                Map.entry(EmployeeRoleMapEntity.class, EmployeeRoleMapDto.class),
                Map.entry(EmployeeEntity.class, EmployeeDto.class),
                Map.entry(RoleEntity.class, RoleDto.class)
        );

        dtoEntityMap = Map.ofEntries(
                Map.entry(EmployeeRoleMapDto.class, EmployeeRoleMapEntity.class),
                Map.entry(EmployeeDto.class, EmployeeEntity.class),
                Map.entry(RoleDto.class, RoleEntity.class)
        );
    }
}
