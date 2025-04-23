package com.sougata.domainApp.employee.repository;

import com.sougata.domainApp.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("select ee from EmployeeEntity ee " +
            "left join fetch ee.roles re " +
            "where ee.isValid = 1")
    List<EmployeeEntity> getAllActiveEmployees();
}
