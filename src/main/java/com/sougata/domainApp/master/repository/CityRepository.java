package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.CityEntity;
import com.sougata.domainApp.master.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    @Query("select ce from CityEntity ce " +
            "where ce.isValid = 1")
    List<CityEntity> findAllActiveCities();

    @Query("select ce from CityEntity ce " +
            "where (ce.district != :district or ce.district is null) and ce.isValid = 1")
    List<CityEntity> findCitiesNotMappedToDist(DistrictEntity district);
}
