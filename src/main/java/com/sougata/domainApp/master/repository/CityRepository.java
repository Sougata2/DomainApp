package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    @Query("select ce from CityEntity ce " +
            "where ce.isValid = 1")
    List<CityEntity> findAllActiveCities();
}
