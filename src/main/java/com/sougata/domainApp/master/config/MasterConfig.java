package com.sougata.domainApp.master.config;

import com.sougata.domainApp.master.mapper.EntityDtoMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MasterConfig {
    @Bean
    public EntityDtoMapping getEntityDtoMapping() {
        return new EntityDtoMapping();
    }
}
