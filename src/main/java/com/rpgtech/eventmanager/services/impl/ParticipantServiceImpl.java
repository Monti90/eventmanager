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
        sessionService.assignUsersToSession(session.registerUser(user), id);
        return "You have been registered to this session, there is "+session.getObservers().size()+" places taken out of"+session.getScenario().getPlayers()+".";
    }

    @Override
    public String assignUserToEvent(Long id) {
        EventEntity event = eventService.findEventById(id);
        UserInfo user = userInfoService.currentlyLoggedUser();
        eventService.assignUsersToEvent(event.registerUser(user), id);
        return "You have been registered to this event, there is "+event.getObservers().size()+" places taken.";
    }

    @Override
    public String assignUnregisteredToSession(ParticipantEntity participant, Long id) {
        SessionEntity session = sessionService.getSessionById(id);
        participantRepository.save(participant);
        sessionService.assignParticipantsToSession(session.registerParticipant(participant), id);
        return "You have been registered to this session, there is "+session.getObservers().size()+" places taken out of"+session.getScenario().getPlayers()+".";
    }

    @Override
    public String assignUnregisteredToEvent(ParticipantEntity participant, Long id) {
        EventEntity event = eventService.findEventById(id);
        participantRepository.save(participant);
        eventService.assignParticipantsToEvent(event.registerParticipant(participant), id);
        return "You have been registered to this event, there is "+event.getObservers().size()+" places taken.";
    }

    @Override
    public String resignLoggedUserSession(Long sessionId, Long id) {
        SessionEntity session = sessionService.getSessionById(sessionId);
        sessionService.assignUsersToSession(session.resignUser(userInfoService.findUserInfoById(id)), sessionId);
        return "You have been removed from this session, there is "+session.getObservers().size()+" places taken out of"+session.getScenario().getPlayers()+".";
    }

    @Override
    public String resignUnregisteredSession(Long id, Long sessionId) {
        SessionEntity session = sessionService.getSessionById(sessionId);
        sessionService.assignParticipantsToSession(session.resignParticipant(participantRepository.findById(id)), sessionId);
        return "You have been removed from this session, there is "+session.getObservers().size()+" places taken out of"+session.getScenario().getPlayers()+".";
    }

    @Override
    public String resignLoggedUserEvent(Long eventId, Long id) {
        EventEntity event = eventService.findEventById(eventId);
        eventService.assignUsersToEvent(event.resignUser(userInfoService.findUserInfoById(id)), eventId);
        return "You have been removed from this event, there is "+event.getObservers().size()+" places taken.";
    }

    @Override
    public String resignUnregisteredEvent(Long id, Long eventId) {
        EventEntity event = eventService.findEventById(eventId);
        eventService.assignParticipantsToEvent(event.resignParticipant(participantRepository.findById(id)), eventId);
        return "You have been removed from this event, there is "+event.getObservers().size()+" places taken.";
    }
}
