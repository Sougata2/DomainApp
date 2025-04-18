package com.sougata.domainApp.menu.controller;

import com.sougata.domainApp.menu.dto.MenuItemDto;
import com.sougata.domainApp.menu.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-item")
@RequiredArgsConstructor
public class MenuItemController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MenuItemService service;

    @GetMapping
    public ResponseEntity<List<MenuItemDto>> getAllActiveMenuItems() {
        logger.info("getAllActiveMenuItems");
        try {
            List<MenuItemDto> menuItems = service.getActiveMenuItems();
            return ResponseEntity.ok(menuItems);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getMenuItemById(@PathVariable Long id) {
        logger.info("getMenuItemById id : {}", id);
        try {
            MenuItemDto menuItemDto = service.getMenuItemById(id);
            return ResponseEntity.ok(menuItemDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MenuItemDto>> getAllActiveMenuItemsOrSubMenuItems() {
        logger.info("getAllActiveMenuItemsOrSubMenuItems");
        try {
            List<MenuItemDto> menuItems = service.getAllActiveMenuItemsOrSubMenuItems();
            return ResponseEntity.ok(menuItems);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody MenuItemDto dto) {
        logger.info("createMenuItem : {}", dto);
        try {
            MenuItemDto created = service.createMenuItem(dto);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<MenuItemDto> updateMenuItem(@RequestBody MenuItemDto dto) {
        logger.info("updateMenuItem : {}", dto);
        try {
            MenuItemDto updated = service.updateMenuItem(dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
