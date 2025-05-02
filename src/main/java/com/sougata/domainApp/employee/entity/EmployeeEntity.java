package com.sougata.domainApp.employee.entity;

import com.sougata.domainApp.role.entity.RoleEntity;
import com.sougata.domainApp.shared.MasterEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class EmployeeEntity implements MasterEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private Integer isValid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RoleEntity defaultRole;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "emp_role_map", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @PrePersist
    protected void onCreate() {
        isValid = 1;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
        // initialize the relation(s)
        this.roles = new HashSet<>();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        Set<String> roleNames = roles.stream().map(RoleEntity::getRoleName).collect(Collectors.toSet());
        return "EmployeeEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isValid=" + isValid +
                ", roles=" + roleNames +
                '}';
    }
}
