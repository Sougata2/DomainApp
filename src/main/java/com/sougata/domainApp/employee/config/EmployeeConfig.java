package com.sougata.domainApp.employee.config;

import com.sougata.domainApp.employee.mapper.EmployeeEntityDtoMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
    @Bean
    public EmployeeEntityDtoMapping employeeEntityDtoMapping() {
        return new EmployeeEntityDtoMapping();
    }
}
