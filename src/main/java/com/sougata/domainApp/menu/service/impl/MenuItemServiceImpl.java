package com.sougata.domainApp.menu.service.impl;

import com.sougata.domainApp.menu.dto.MenuItemDto;
import com.sougata.domainApp.menu.entity.MenuItemEntity;
import com.sougata.domainApp.menu.mapper.MenuEntityDtoMapping;
import com.sougata.domainApp.menu.repository.MenuItemRepository;
import com.sougata.domainApp.menu.repository.MenuSubItemRepository;
import com.sougata.domainApp.menu.service.MenuItemService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final MenuSubItemRepository menuSubItemRepository;
    private final MenuEntityDtoMapping entityDtoMapping;

    @Override
    public List<MenuItemDto> getActiveMenuItems() {
        return menuItemRepository.findActiveMenuItems().stream()
                .map(e -> (MenuItemDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap()))
                .toList();
    }

    @Override
    public MenuItemDto createMenuItem(MenuItemDto dto) {
        MenuItemEntity entity = (MenuItemEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        MenuItemEntity saved = menuItemRepository.save(entity);
        return (MenuItemDto) RelationMapper.mapToDto(saved, entityDtoMapping.getEntityDtoMap());
    }
}
