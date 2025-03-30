package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, UUID> {
}
