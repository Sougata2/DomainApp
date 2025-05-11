package com.sougata.domainApp.employeeRoleMap.repository;

import com.sougata.domainApp.employeeRoleMap.entity.EmployeeRoleMapEntity;
import com.sougata.domainApp.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRoleMapRepository extends JpaRepository<EmployeeRoleMapEntity, Long> {
    @Query("select erm from EmployeeRoleMapEntity erm " +
            "left join fetch erm.employee ee " +
            "left join fetch erm.role re " +
            "where ee.id = :employeeId and erm.isValid = 1")
    List<EmployeeRoleMapEntity> findEmployeeRoleMapByEmployeeId(Long employeeId);

    @Query("select erm from EmployeeRoleMapEntity erm " +
            "left join fetch erm.employee ee " +
            "left join fetch erm.role re " +
            "where ee.id = :employeeId and re.id = :roleId and erm.isValid = 1")
    Optional<EmployeeRoleMapEntity> findEmployeeRoleMapByEmployeeIdAndRoleId(Long employeeId, Long roleId);

    @Query("select re from RoleEntity re " +
            "left outer join re.roleMappings rm " +
            "where (rm.employee.id != :employeeId or rm.employee.id is null) " +
            "and re.isValid = 1")
    List<RoleEntity> findNotAssignedRolesByEmployeeId(Long employeeId);
}
