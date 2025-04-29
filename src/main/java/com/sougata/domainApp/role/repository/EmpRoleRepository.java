package com.sougata.domainApp.role.repository;

import com.sougata.domainApp.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpRoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("select re from RoleEntity re " +
            "left join re.employees ee " +
            "where re.isValid = 1")
    List<RoleEntity> findAllActiveRoles();

    @Query("select re from RoleEntity re " +
            "left join re.employees ee " +
            "where re.id = :id")
    Optional<RoleEntity> findRoleWithEmployeeById(Long id);
}
