package com.sougata.domainApp.employeeRoleMap.entity;

import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.role.entity.RoleEntity;
import com.sougata.domainApp.shared.MasterEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emp_role_map_custom", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"emp_id", "role_id"})
})
public class EmployeeRoleMapEntity implements MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_id")
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column
    private Integer isDefault;

    @Column
    private Integer isValid;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
        isValid = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "EmployeeRoleMapEntity{" +
                "Id=" + Id +
                ", employee=" + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName() +
                ", role=" + role.getRoleName() +
                ", isDefault=" + isDefault +
                ", isValid=" + isValid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
