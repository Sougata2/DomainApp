package com.sougata.domainApp.master.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country_state_map")
public class CountryStateMappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    private StateEntity state;

    @Column
    private Integer isValid;

    @PrePersist
    protected void onCreate() {
        isValid = 1;
    }
}
