package com.sougata.domainApp.menu.entity;

import com.sougata.domainApp.shared.MasterEntity;
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
@Table(name = "menu_items")
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemEntity implements MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String menuItemName;

    @Column
    private String pageLink;

    @Column
    private Integer isValid;

    @Column
    private LocalDateTime logDate;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MenuItemEntity> menuItems;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItemEntity menuItem;

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
