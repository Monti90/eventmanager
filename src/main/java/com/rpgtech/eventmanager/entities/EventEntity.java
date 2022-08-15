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
import java.util.HashSet;
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
    @OneToMany
    private Set<ParticipantEntity> participants = new HashSet<ParticipantEntity>();
    @OneToMany
    private Set<UserInfo> users = new HashSet<UserInfo>();
    @OneToMany
    private Set<SessionEntity> sessions = new HashSet<SessionEntity>();


    @Override
    public void registerParticipant(ParticipantEntity participant) {
        participants.add(participant);
    }

    @Override
    public void registerUser(UserInfo user) {
        users.add(user);
    }

    @Override
    public Set<Observer> getObservers() {
        Set<Observer> players = new HashSet<Observer>();
        for (ParticipantEntity participant : participants) {
            players.add(participant);
        }
        for (UserInfo user : users) {
            players.add(user);
        }
        return players;
    }

    @Override
    public void deleteObserver(Observer observer) {
        if (participants.contains(observer)) {
            participants.remove(observer);
        }
        else{
            users.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        Set<Observer> players = getObservers();
        for(Observer observer : players){
            observer.update(startsAt, endsAt, isActive, sessions);
        }
    }
}
