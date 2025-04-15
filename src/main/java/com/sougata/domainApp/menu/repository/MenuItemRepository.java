package com.sougata.domainApp.menu.repository;

import com.sougata.domainApp.menu.entity.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {

    @Query("select mi from MenuItemEntity mi " +
            "left join mi.subItems " +
            "where mi.isValid = 1")
    List<MenuItemEntity> findActiveMenuItems();
}
