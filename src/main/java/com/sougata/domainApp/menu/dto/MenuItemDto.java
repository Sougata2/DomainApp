package com.sougata.domainApp.menu.dto;

import com.sougata.domainApp.shared.MasterDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.sougata.domainApp.menu.entity.MenuItemEntity}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto implements MasterDto {
    private Long id;
    private String menuItemName;
    private String pageLink;
    private Integer isValid;
    private LocalDateTime logDate;
    private List<MenuItemDto> menuItems;
    private MenuItemDto menuItem;
}