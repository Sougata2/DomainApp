package com.sougata.domainApp.menu.service;


import com.sougata.domainApp.menu.dto.MenuItemDto;

import java.util.List;

public interface MenuItemService {
    List<MenuItemDto> getActiveMenuItems();

    List<MenuItemDto> getAllActiveMenuItemsOrSubMenuItems();

    MenuItemDto getMenuItemById(Long id);

    MenuItemDto createMenuItem(MenuItemDto dto);

    MenuItemDto updateMenuItem(MenuItemDto dto);
}
