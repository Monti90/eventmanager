package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import java.util.Set;

public interface ParticipantService {
    String assignUserToSession(Long id);

    String assignUserToEvent(Long id);

    String assignUnregisteredToSession(ParticipantEntity participant, Long id);

    String assignUnregisteredToEvent(ParticipantEntity participant, Long id);

    String resignLoggedUserSession(Long sessionId, Long id);

    String resignUnregisteredSession(Long id, Long sessionId);

    String resignLoggedUserEvent(Long eventId, Long id);

    String resignUnregisteredEvent(Long id, Long eventId);
}
