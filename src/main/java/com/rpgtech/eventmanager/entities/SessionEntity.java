package com.rpgtech.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.rpgtech.eventmanager.builders.SessionBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sessions")
@NoArgsConstructor
public class SessionEntity {

    @Id
    @SequenceGenerator(name = "session_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_generator")
    private Long id;
    private String meetingLink;
    @Column(nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startsAt;
    @Column(nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endsAt;
    @ManyToOne
    private UserInfo host;
    @ManyToOne
    private EventEntity event;
    @ManyToOne
    private ScenarioEntity scenario;
    private boolean isActive;

    public SessionEntity(SessionBuilder builder){
        this.meetingLink = builder.getMeetingLink();
        this.startsAt = builder.getStartTime();
        this.host = builder.getHost();
        this.event = builder.getEvent();
        this.scenario = builder.getScenario();
    }
}
