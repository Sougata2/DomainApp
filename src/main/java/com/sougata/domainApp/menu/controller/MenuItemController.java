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
}
