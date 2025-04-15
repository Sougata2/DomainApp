package com.sougata.domainApp.menu.repository;

import com.sougata.domainApp.menu.dto.MenuSubItemEntityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuSubItemRepository extends JpaRepository<MenuSubItemEntityDto, Long> {
}
