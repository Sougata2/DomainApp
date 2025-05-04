package com.sougata.domainApp.employeeRoleMap.config;

import com.sougata.domainApp.employeeRoleMap.mapper.EmployeeRoleMapMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeRoleMapConfig {
    @Bean
    public EmployeeRoleMapMapping employeeRoleMapMapping() {
        return new EmployeeRoleMapMapping();
    }
}
