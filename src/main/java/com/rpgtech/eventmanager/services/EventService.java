package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.entities.UserInfo;

import java.util.List;
import java.util.Set;

public interface EventService {

    EventEntity addEvent(EventEntity event);
    EventEntity updateEvent(EventEntity event, Long id);
    List<EventEntity> getEvents();
    EventEntity findEventById(Long id);
    void assignParticipantsToEvent(Set<ParticipantEntity> participants, Long id);
    void assignUsersToEvent(Set<UserInfo> users, Long id);
    EventEntity cancelEvent(Long id);
}
