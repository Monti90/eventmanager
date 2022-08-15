package com.rpgtech.eventmanager.entities;

import java.util.Collection;
import java.util.Set;

public interface EventObserver {
    public void registerParticipant(ParticipantEntity participant);
    public void registerUser(UserInfo user);
    public Set<Observer> getObservers();

    public void deleteObserver(Observer observer);
    public void notifyObservers();
}
