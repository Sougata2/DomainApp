package com.sougata.domainApp.menu.dto;

import com.sougata.domainApp.menu.entity.MenuSubItemEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.sougata.domainApp.menu.entity.MenuItemEntity}
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto implements Serializable {
    private Long id;
    private String menuItemName;
    private Integer isValid;
    private LocalDateTime logDate;
    private List<MenuSubItemEntityDto> subItems;
    private MenuSubItemEntityDto menuSubItem;
}