package com.rpgtech.eventmanager.entities;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.ScenarioEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "sessions")
public class SessionEntity {

    @Id
    @SequenceGenerator(name = "session_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_generator")
    protected Long id;
    protected Long peopleLimit;
    protected String meetingLink;
    protected LocalDate meetingDate;
    @ManyToOne
    protected UserInfo host;
    @ManyToOne
    protected EventEntity event;
    @ManyToOne
    protected ScenarioEntity scenario;
    protected String description;

}
