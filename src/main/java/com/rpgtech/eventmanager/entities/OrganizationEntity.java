package com.rpgtech.eventmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "organizations")
@NoArgsConstructor
public class OrganizationEntity implements Serializable {

    @Id
    @SequenceGenerator(
            name = "organization_sequence",
            sequenceName = "organization_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "organization_sequence"
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    private String organizationName;

    public OrganizationEntity(String organizationName) {
        this.organizationName = organizationName;
    }
}
