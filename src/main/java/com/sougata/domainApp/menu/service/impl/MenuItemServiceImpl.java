package com.sougata.domainApp.menu.service.impl;

import com.sougata.domainApp.menu.dto.MenuItemDto;
import com.sougata.domainApp.menu.entity.MenuItemEntity;
import com.sougata.domainApp.menu.mapper.MenuEntityDtoMapping;
import com.sougata.domainApp.menu.repository.MenuItemRepository;
import com.sougata.domainApp.menu.service.MenuItemService;
import com.sougata.domainApp.shared.RelationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository repository;
    private final MenuEntityDtoMapping entityDtoMapping;

    @Override
    public List<MenuItemDto> getActiveMenuItems() {
        return repository.findActiveMenuItems().stream()
                .map(e -> (MenuItemDto) RelationMapper.mapToDto(e, entityDtoMapping.getEntityDtoMap()))
                .toList();
    }

    @Override
    public List<MenuItemDto> getAllActiveMenuItemsOrSubMenuItems() {
        return repository.findAll()
                .stream()
                .map(e ->
                        (MenuItemDto) RelationMapper
                                .mapToDto(e, entityDtoMapping.getEntityDtoMap())
                ).toList();
    }

    @Override
    public MenuItemDto createMenuItem(MenuItemDto dto) {
        MenuItemEntity entity = (MenuItemEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        MenuItemEntity saved = repository.save(entity);
        return (MenuItemDto) RelationMapper.mapToDto(saved, entityDtoMapping.getEntityDtoMap());
    }

    @Override
    public MenuItemDto updateMenuItem(MenuItemDto dto) {
        Optional<MenuItemEntity> og = repository.findById(dto.getId());
        if (og.isEmpty()) return null;
        MenuItemEntity nu = (MenuItemEntity) RelationMapper.mapToEntity(dto, entityDtoMapping.getDtoEntityMap());
        MenuItemEntity merged = (MenuItemEntity) RelationMapper.merge(og.get(), nu);
        MenuItemEntity saved = repository.save(merged);
        return (MenuItemDto) RelationMapper.mapToDto(saved, entityDtoMapping.getEntityDtoMap());
    }
}
