package com.sougata.domainApp.role.entity;

import com.sougata.domainApp.employeeRoleMap.entity.EmployeeRoleMapEntity;
import com.sougata.domainApp.shared.MasterEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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


    /**
     * @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
     * @JoinTable(name = "emp_role_map", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "emp_id"))
     * private Set<EmployeeEntity> employees;
     */

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EmployeeRoleMapEntity> roleMappings;

    @PrePersist
    protected void onCreate() {
        isValid = 1;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        roleName = "ROLE_" + roleName.toUpperCase();
        this.roleMappings = new HashSet<>();
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
        return "RoleEntity{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", isValid=" + isValid +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", roleMappings=" + roleMappings +
                '}';
    }
}
