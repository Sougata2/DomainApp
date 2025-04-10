package com.sougata.domainApp.master.repository;

import com.sougata.domainApp.master.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {
    @Query("select se " +
            "from StateEntity se " +
            "left join fetch se.districts de " +
            "where se.isValid = 1")
    List<StateEntity> findAllActiveStates();
}
