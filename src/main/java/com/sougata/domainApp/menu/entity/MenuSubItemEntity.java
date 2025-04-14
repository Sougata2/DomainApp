package com.sougata.domainApp.menu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_sub_items")
public class MenuSubItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menuSubItemName;

    @Column
    private Integer isValid;

    @Column
    private LocalDateTime logDate;

    @Column
    private String pageLink;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItemEntity menuItem;

    @OneToMany(mappedBy = "menuSubItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MenuItemEntity> menuItems;

    @PrePersist
    protected void onCreate() {
        isValid = 1;
        logDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        logDate = LocalDateTime.now();
    }

}
