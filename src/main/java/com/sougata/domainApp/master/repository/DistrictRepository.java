package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, UUID> {
    @Query("select d from DistrictEntity d join fetch d.cities c where d.isActive = 1 and c.isActive = 1")
    List<DistrictEntity> findAllDistrictsWithCity();

    @Query("select d from DistrictEntity d join fetch d.cities c where d.distId = :distId and c.isActive = 1")
    Optional<DistrictEntity> findDistrictWithCity(UUID distId);
}
