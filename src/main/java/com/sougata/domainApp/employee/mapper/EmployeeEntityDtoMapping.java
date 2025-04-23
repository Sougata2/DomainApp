package com.sougata.domainApp.employee.mapper;

import com.sougata.domainApp.employee.dto.EmployeeDto;
import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.role.entity.RoleEntity;
import com.sougata.domainApp.shared.MasterDto;
import com.sougata.domainApp.shared.MasterEntity;
import lombok.Getter;

import java.util.Map;

@Getter
public class EmployeeEntityDtoMapping {
    private final Map<Class<? extends MasterEntity>, Class<? extends MasterDto>> entityDtoMap;
    private final Map<Class<? extends MasterDto>, Class<? extends MasterEntity>> dtoEntityMap;

    public EmployeeEntityDtoMapping() {
        entityDtoMap = Map.ofEntries(
                Map.entry(EmployeeEntity.class, EmployeeDto.class),
                Map.entry(RoleEntity.class, RoleDto.class)
        );

        dtoEntityMap = Map.ofEntries(
                Map.entry(EmployeeDto.class, EmployeeEntity.class),
                Map.entry(RoleDto.class, RoleEntity.class)
        );
    }
}
