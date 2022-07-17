package com.rpgtech.eventmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "participants")
public class ParticipantEntity {

    @Id
    private Long id;
    private String nickName;
    private String email;
    private String discordName;
    private boolean isRegistered;
    @ManyToOne
    private EventEntity event;
    @ManyToOne
    private SessionEntity session;
}
