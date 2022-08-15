package com.rpgtech.eventmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "participants")
public class ParticipantEntity implements Observer {

    @Id
    private Long id;
    private String nickName;
    private String email;
    private String discordName;

    @Override
    public void update(LocalDateTime startTime, LocalDateTime endTime, boolean isActive, Collection<?> collection) {

    }
}
