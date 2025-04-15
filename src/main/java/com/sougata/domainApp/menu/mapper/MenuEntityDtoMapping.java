package com.sougata.domainApp.menu.mapper;


import com.sougata.domainApp.menu.dto.MenuItemDto;
import com.sougata.domainApp.menu.dto.MenuSubItemDto;
import com.sougata.domainApp.menu.entity.MenuItemEntity;
import com.sougata.domainApp.menu.entity.MenuSubItemEntity;
import com.sougata.domainApp.shared.MasterDto;
import com.sougata.domainApp.shared.MasterEntity;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Component
public class MenuEntityDtoMapping {
    private final Map<Class<? extends MasterEntity>, Class<? extends MasterDto>> entityDtoMap;
    private final Map<Class<? extends MasterDto>, Class<? extends MasterEntity>> dtoEntityMap;

    public MenuEntityDtoMapping() {
        entityDtoMap = Map.ofEntries(
                Map.entry(MenuItemEntity.class, MenuItemDto.class),
                Map.entry(MenuSubItemEntity.class, MenuSubItemDto.class)
        );

        dtoEntityMap = Map.ofEntries(
                Map.entry(MenuItemDto.class, MenuItemEntity.class),
                Map.entry(MenuSubItemDto.class, MenuSubItemEntity.class)
        );
    }

}
