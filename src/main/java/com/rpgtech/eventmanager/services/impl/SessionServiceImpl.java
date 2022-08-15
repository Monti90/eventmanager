package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.builders.SessionBuilder;
import com.rpgtech.eventmanager.dto.SessionCreateDTO;
import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.ScenarioEntity;
import com.rpgtech.eventmanager.entities.SessionEntity;
import com.rpgtech.eventmanager.exceptions.SessionNotFoundException;
import com.rpgtech.eventmanager.exceptions.SessionNotInEventTimeException;
import com.rpgtech.eventmanager.repositories.SessionRepository;
import com.rpgtech.eventmanager.services.EventService;
import com.rpgtech.eventmanager.services.ScenarioService;
import com.rpgtech.eventmanager.services.SessionService;
import com.rpgtech.eventmanager.services.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final ScenarioService scenarioService;
    private final UserInfoService userInfoService;
    private final EventService eventService;
    @Override
    public List<SessionEntity> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public SessionEntity getSessionById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Session with id:"+id+" not found!"));
    }

    @Override
    @Transactional
    public SessionEntity addSession(SessionCreateDTO session) {
        ScenarioEntity scenario = scenarioService.getScenarioById(session.getScenarioID());
        EventEntity event = handleSessionEvent(session, scenario);
        SessionEntity sessionEntity = new SessionBuilder(session.getMeetingLink(),
                                        userInfoService.currentlyLoggedUser(),
                                        session.getStartsAt(),
                                        scenario)
                .event(event)
                .build();
        sessionEntity.setActive(true);
        sessionEntity.setEndsAt(calculateSessionEndTime(session.getStartsAt(),
                scenario.getDurationHours(),
                scenario.getDurationMinutes()));
        return sessionRepository.save(sessionEntity);
    }

    private EventEntity handleSessionEvent(SessionCreateDTO session, ScenarioEntity scenario){
        if(session.getEventID() != null){
            EventEntity event = eventService.findEventById(session.getEventID());
            if(!isSessionInEvent(event, session.getStartsAt(), scenario)){
                throw new SessionNotInEventTimeException("This session time does not fit in the event time!");
            }
            return event;
        }
        List<EventEntity> eventsThatFitTime = eventService.getEvents()
                                                            .stream()
                                                            .filter(event -> isSessionInEvent(event, session.getStartsAt(), scenario))
                                                            .collect(Collectors.toList());
        return null;
    }

    private boolean isSessionInEvent(EventEntity event, LocalDateTime startTime, ScenarioEntity scenario) {
        return event.getStartsAt().isBefore(startTime)
                && event.getEndsAt()
                        .isAfter(calculateSessionEndTime(startTime,
                                scenario.getDurationHours(),
                                scenario.getDurationMinutes()));
    }

    @Override
    public SessionEntity updateSession(SessionCreateDTO session, Long id) {
        SessionEntity sessionEntity = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Session with id:"+id+" not found!"));
        ScenarioEntity scenario = scenarioService.getScenarioById(session.getScenarioID());
        EventEntity event = null;
        if(session.getEventID() != null){
            event = eventService.findEventById(session.getEventID());
        }
        sessionEntity = new SessionBuilder(session.getMeetingLink(),
                userInfoService.currentlyLoggedUser(),
                session.getStartsAt(),
                scenario)
                .event(event)
                .build();
        sessionEntity.setActive(true);
        sessionEntity.setEndsAt(calculateSessionEndTime(session.getStartsAt(),
                scenario.getDurationHours(),
                scenario.getDurationMinutes()));
        sessionEntity.setId(id);
        return sessionRepository.save(sessionEntity);
    }

    @Override
    public SessionEntity cancelSession(Long id) {
        SessionEntity session = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException("Session with id:"+id+" not found!"));
        session.setActive(false);
        return sessionRepository.save(session);
    }

    public LocalDateTime calculateSessionEndTime(LocalDateTime sessionStart, Long hours, Long minutes)
    {
        return sessionStart
                .plusHours(hours)
                .plusMinutes(minutes);
    }
}
