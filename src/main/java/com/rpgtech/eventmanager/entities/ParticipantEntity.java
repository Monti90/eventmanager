package com.rpgtech.eventmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "participants")
public class ParticipantEntity implements Observer {

    @Id
    @SequenceGenerator(name = "participant_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_generator")
    private Long id;
    private String email;
    private String discordName;

    public Long getId(){
        return id;
    }

}
