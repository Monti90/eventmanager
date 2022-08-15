package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.entities.SessionEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import com.rpgtech.eventmanager.repositories.ParticipantRepository;
import com.rpgtech.eventmanager.services.EventService;
import com.rpgtech.eventmanager.services.ParticipantService;
import com.rpgtech.eventmanager.services.SessionService;
import com.rpgtech.eventmanager.services.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    ParticipantRepository participantRepository;
    EventService eventService;
    SessionService sessionService;
    UserInfoService userInfoService;

    @Override
    public String assignUserToSession(Long id) {
        SessionEntity session = sessionService.getSessionById(id);
        UserInfo user = userInfoService.currentlyLoggedUser();
        session.registerUser(user);
        return "You have been registered to this session, there is "+session.getObservers().size()+" places taken out of"+session.getScenario().getPlayers()+".";
    }

    @Override
    public String assignUserToEvent(Long id) {
        EventEntity event = eventService.findEventById(id);
        UserInfo user = userInfoService.currentlyLoggedUser();
        event.registerUser(user);
        return "You have been registered to this session, there is "+event.getObservers().size()+" places taken.";
    }

    @Override
    public String assignUnregisteredToSession(ParticipantEntity participant, Long id) {
        SessionEntity session = sessionService.getSessionById(id);
        participantRepository.save(participant);
        session.registerParticipant(participant);
        return "You have been registered to this session, there is "+session.getObservers().size()+" places taken out of"+session.getScenario().getPlayers()+".";
    }

    @Override
    public String assignUnregisteredToEvent(ParticipantEntity participant, Long id) {
        EventEntity event = eventService.findEventById(id);
        participantRepository.save(participant);
        event.registerParticipant(participant);
        return "You have been registered to this session, there is "+event.getObservers().size()+" places taken.";
    }
}
