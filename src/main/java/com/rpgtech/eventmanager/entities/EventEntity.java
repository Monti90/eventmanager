package com.rpgtech.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.rpgtech.eventmanager.exceptions.ParticipantNotFoundException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
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
    @ManyToMany
    private Set<UserInfo> users = new HashSet<UserInfo>();
    @OneToMany
    private Set<SessionEntity> sessions = new HashSet<SessionEntity>();


    @Override
    public Set<ParticipantEntity> registerParticipant(ParticipantEntity participant) {
        participants.add(participant);
        return  participants;
    }

    @Override
    public Set<UserInfo> registerUser(UserInfo user) {
        users.add(user);
        return users;
    }

    @Override
    public Set<ParticipantEntity> resignParticipant(Optional<ParticipantEntity> participant) {
        if(participant.isPresent()){
            participants.remove(participant.get());
            return participants;
        }
        throw new ParticipantNotFoundException("Participant with this id does not exist in this event");
    }

    @Override
    public Set<UserInfo> resignUser(UserInfo user) {
        users.remove(user);
        return users;
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
}
