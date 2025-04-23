package com.sougata.domainApp.role.config;

import com.sougata.domainApp.role.mapper.RoleEntityDtoMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {
    @Bean
    public RoleEntityDtoMapping roleEntityDtoMapping() {
        return new RoleEntityDtoMapping();
    }
}
