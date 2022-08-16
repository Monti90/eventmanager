package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.dto.SessionCreateDTO;
import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.entities.SessionEntity;
import com.rpgtech.eventmanager.entities.UserInfo;

import java.util.List;
import java.util.Set;

public interface SessionService{

    List<SessionEntity> getAllSessions();

    SessionEntity getSessionById(Long id);

    SessionEntity addSession(SessionCreateDTO session);

    SessionEntity updateSession(SessionCreateDTO session, Long id);
    void assignParticipantsToSession(Set<ParticipantEntity> participants, Long id);
    void assignUsersToSession(Set<UserInfo> users, Long id);

    SessionEntity cancelSession(Long id);
    SessionEntity appendSessionToEvent(Long id, Long eventId);
}
