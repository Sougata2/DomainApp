package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {
}
