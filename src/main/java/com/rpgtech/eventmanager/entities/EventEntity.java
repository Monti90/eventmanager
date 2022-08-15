package com.rpgtech.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "events")
public class EventEntity implements Serializable, EventObserver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
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
    private OrganizationEntity organization;
    private String description;
    private boolean isActive;
    private Set<Observer> participants;
    private Set<SessionEntity> sessions;

    @Override
    public void registerObserver(Observer observer) {
            participants.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
            participants.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : participants){
            observer.update(startsAt, endsAt, isActive, sessions);
        }
    }
}
