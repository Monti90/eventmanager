package com.rpgtech.eventmanager.entities;

import java.util.Optional;
import java.util.Set;

public interface EventObserver {
    public Set<ParticipantEntity> registerParticipant(ParticipantEntity participant);
    public Set<UserInfo> registerUser(UserInfo user);
    public Set<ParticipantEntity> resignParticipant(Optional<ParticipantEntity> participant);
    public Set<UserInfo> resignUser(UserInfo user);
    public Set<Observer> getObservers();

    public void deleteObserver(Observer observer);
}
