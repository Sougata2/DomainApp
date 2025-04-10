package com.sougata.domainApp.master.entity;

import com.sougata.domainApp.shared.MasterEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "states")
public class StateEntity implements MasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String stateName;

    @Column
    private Integer isValid;

    @Column
    private LocalDateTime logDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    private List<DistrictEntity> districts;

    @PrePersist
    protected void onCreate() {
        isValid = 1;
        logDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        logDate = LocalDateTime.now();
    }

    @Override
    public Long getId() {
        return id;
    }
}
