package com.rpgtech.eventmanager.entities;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.ScenarioEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sessions")
public class SessionEntity {

    @Id
    @SequenceGenerator(name = "session_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_generator")
    private Long id;
    private Long peopleLimit;
    private String meetingLink;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    @ManyToOne
    private UserInfo host;
    @ManyToOne
    private EventEntity event;
    @ManyToOne
    private ScenarioEntity scenario;
    private String description;
    private boolean isActive;
}
