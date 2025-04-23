package com.sougata.domainApp.role.entity;

import com.sougata.domainApp.employee.entity.EmployeeEntity;
import com.sougata.domainApp.shared.MasterEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emp_roles")
public class RoleEntity implements MasterEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleName;

    @Column
    private Integer isValid;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "emp_role_map", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "emp_id"))
    private Set<EmployeeEntity> employees;

    @PrePersist
    protected void onCreate() {
        isValid = 1;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        roleName = "ROLE_" + roleName.toUpperCase();
        this.employees = new HashSet<>();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName.toUpperCase();
        }
    }

    @Override
    public String toString() {
        Set<String> employeeNames = this.employees.stream().map(EmployeeEntity::getEmail).collect(Collectors.toSet());
        return "RoleEntity{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", isValid=" + isValid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", employees=" + employeeNames +
                '}';
    }
}
