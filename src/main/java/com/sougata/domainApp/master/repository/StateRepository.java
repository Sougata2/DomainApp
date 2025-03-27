package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<StateEntity, UUID> {
}
