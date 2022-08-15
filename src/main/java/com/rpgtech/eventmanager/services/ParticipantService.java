package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import java.util.Set;

public interface ParticipantService {
    String assignUserToSession(Long id);

    String assignUserToEvent(Long id);

    String assignUnregisteredToSession(ParticipantEntity participant, Long id);

    String assignUnregisteredToEvent(ParticipantEntity participant, Long id);
}
