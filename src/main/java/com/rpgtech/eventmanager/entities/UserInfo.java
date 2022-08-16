package com.rpgtech.eventmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable, Observer {

    @Id
    @SequenceGenerator(
            name = "user_info_sequence",
            sequenceName = "user_info_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_info_sequence"
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    private String discordName;
    private String email;
    private String phoneNumber;
    @ManyToOne
    private OrganizationEntity organization;

    public UserInfo(String discordName, String phoneNumber, String email){
        this.discordName = discordName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
