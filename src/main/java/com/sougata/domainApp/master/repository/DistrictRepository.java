package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    @Query("select de from DistrictEntity de " +
            "left join fetch de.cities ce " +
            "where de.isValid = 1")
    List<DistrictEntity> findAllActiveDistricts();

    @Query("select de from DistrictEntity de " +
            "join fetch de.cities ce " +
            "where de.isValid = 1 and ce.isValid = 1")
    List<DistrictEntity> findMappedDistricts();

    @Query("select de from DistrictEntity de " +
            "where de.id = :id and de.isValid = 1")
    Optional<DistrictEntity> findDistrictById(Long id);
}
