package com.sougata.domainApp.role.mapper;

import com.sougata.domainApp.employee.dto.EmployeeDto;
import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.role.dto.RoleDto;
import com.sougata.domainApp.role.entity.RoleEntity;
import com.sougata.domainApp.shared.MasterDto;
import com.sougata.domainApp.shared.MasterEntity;
import lombok.Getter;

import java.util.Map;

@Getter
public class RoleEntityDtoMapping {
    private final Map<Class<? extends MasterEntity>, Class<? extends MasterDto>> entityDtoMap;
    private final Map<Class<? extends MasterDto>, Class<? extends MasterEntity>> dtoEntityMap;

    public RoleEntityDtoMapping() {
        entityDtoMap = Map.ofEntries(
                Map.entry(RoleEntity.class, RoleDto.class),
                Map.entry(EmployeeEntity.class, EmployeeDto.class)
        );
        dtoEntityMap = Map.ofEntries(
                Map.entry(RoleDto.class, RoleEntity.class),
                Map.entry(EmployeeDto.class, EmployeeEntity.class)
        );
    }
}
