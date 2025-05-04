package com.sougata.domainApp.employeeRoleMap.mapper;

import com.sougata.domainApp.employeeRoleMap.dto.EmployeeRoleMapDto;
import com.sougata.domainApp.employeeRoleMap.entity.EmployeeRoleMapEntity;
import com.sougata.domainApp.shared.MasterDto;
import com.sougata.domainApp.shared.MasterEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Component
public class EmployeeRoleMapMapping {
    private final Map<Class<? extends MasterEntity>, Class<? extends MasterDto>> entityDtoMap;
    private final Map<Class<? extends MasterDto>, Class<? extends MasterEntity>> dtoEntityMap;

    public EmployeeRoleMapMapping() {
        entityDtoMap = Map.ofEntries(
                Map.entry(EmployeeRoleMapEntity.class, EmployeeRoleMapDto.class)
        );

        dtoEntityMap = Map.ofEntries(
                Map.entry(EmployeeRoleMapDto.class, EmployeeRoleMapEntity.class)
        );
    }
}
