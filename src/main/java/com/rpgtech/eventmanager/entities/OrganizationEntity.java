package com.rpgtech.eventmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "organizations")
public class OrganizationEntity implements Serializable {

    @Id
    private Long id;
    private String organizationName;
    @OneToMany(mappedBy = "organization")
    private Set<UserInfo> organizationMembers;
    @OneToMany(mappedBy = "organization")
    private Set<EventEntity> events;
}
