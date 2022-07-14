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
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    @Id
    private Long id;
    private String discordName;
    private String phoneNumber;
    @OneToMany(mappedBy = "userInfo")
    private Set<ScenarioEntity> scenarios;
    @OneToMany(mappedBy = "host")
    private Set<SessionEntity> sessions;
    @ManyToOne
    private OrganizationEntity organization;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
}
