package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.dto.SessionCreateDTO;
import com.rpgtech.eventmanager.entities.SessionEntity;

import java.util.List;

public interface SessionService{

    List<SessionEntity> getAllSessions();

    SessionEntity getSessionById(Long id);

    SessionEntity addSession(SessionCreateDTO session);

    SessionEntity updateSession(SessionCreateDTO session, Long id);

    SessionEntity cancelSession(Long id);
}
