package com.sougata.domainApp.menu.dto;

import com.sougata.domainApp.menu.entity.MenuItemEntity;
import com.sougata.domainApp.shared.MasterDto;
import lombok.*;

import java.io.Serializable;
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
    private Integer isValid;
    private LocalDateTime logDate;
    private List<MenuItemDto> menuItems;
}