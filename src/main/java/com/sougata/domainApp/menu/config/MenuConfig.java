package com.sougata.domainApp.menu.config;

import com.sougata.domainApp.menu.mapper.MenuEntityDtoMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuConfig {
    @Bean
    public MenuEntityDtoMapping menuEntityDtoMapping() {
        return new MenuEntityDtoMapping();
    }
}
