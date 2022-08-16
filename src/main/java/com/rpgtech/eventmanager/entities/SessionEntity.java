package com.rpgtech.eventmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.rpgtech.eventmanager.builders.SessionBuilder;
import com.rpgtech.eventmanager.exceptions.ParticipantNotFoundException;
import com.rpgtech.eventmanager.exceptions.SessionIsFullException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sessions")
@NoArgsConstructor
public class SessionEntity implements EventObserver {

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
    @OneToMany
    private Set<ParticipantEntity> participants = new HashSet<ParticipantEntity>();
    @ManyToMany
    private Set<UserInfo> users = new HashSet<UserInfo>();

    public SessionEntity(SessionBuilder builder){
        this.meetingLink = builder.getMeetingLink();
        this.startsAt = builder.getStartTime();
        this.host = builder.getHost();
        this.event = builder.getEvent();
        this.scenario = builder.getScenario();
    }

    @Override
    public Set<ParticipantEntity> registerParticipant(ParticipantEntity participant) {
        if(participants.size()+users.size() < scenario.getPlayers()) {
            participants.add(participant);
            return  participants;
        }
        else {
            throw new SessionIsFullException("This session is already full");
        }
    }

    @Override
    public Set<UserInfo> registerUser(UserInfo user) {
        if(participants.size()+users.size() < scenario.getPlayers()) {
            users.add(user);
            return users;
        }
        else {
            throw new SessionIsFullException("This session is already full");
        }
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
