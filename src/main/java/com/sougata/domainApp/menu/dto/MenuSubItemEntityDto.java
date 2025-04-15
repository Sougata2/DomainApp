package com.sougata.domainApp.menu.dto;

import com.sougata.domainApp.menu.entity.MenuItemEntity;
import com.sougata.domainApp.menu.entity.MenuSubItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link MenuSubItemEntity}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuSubItemEntityDto implements Serializable {
    private Long id;
    private String menuSubItemName;
    private Integer isValid;
    private LocalDateTime logDate;
    private String pageLink;
    private MenuItemDto menuItem;
    private List<MenuItemDto> menuItems;
}