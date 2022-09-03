package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.email.EmailSender;
import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.Observer;
import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import com.rpgtech.eventmanager.exceptions.BadTimeSpanException;
import com.rpgtech.eventmanager.exceptions.EventNotFoundException;
import com.rpgtech.eventmanager.exceptions.UserNotInOrganizationException;
import com.rpgtech.eventmanager.repositories.EventRepository;
import com.rpgtech.eventmanager.services.EventService;
import com.rpgtech.eventmanager.services.RegistrationService;
import com.rpgtech.eventmanager.services.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserInfoService userInfoService;
    private final EmailSender emailSender;

    @Override
    public EventEntity addEvent(EventEntity event) {
        UserInfo currentUser = userInfoService.currentlyLoggedUser();
        if(currentUser.getOrganization() != null){
            checkEvent(event);
            event.setOrganization(currentUser.getOrganization());
            event.setActive(true);
            return eventRepository.save(event);
        }
        else{
            throw new UserNotInOrganizationException("User does not belong to any organization");
        }
    }

    public void checkEvent(EventEntity event){
        if(!event.getStartsAt().isBefore(event.getEndsAt())){
            throw new BadTimeSpanException("Event start date is after event end date!");
        }
        if(!event.getStartsAt().isAfter(LocalDateTime.now())){
            throw new BadTimeSpanException("Event start date must occur in the future!");
        }
    }

    @Override
    public EventEntity updateEvent(EventEntity event, Long id) {
        checkEvent(event);
        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with ID: "+id+" not found"));
        if(!eventEntity.getOrganization().equals(userInfoService.currentlyLoggedUser().getOrganization())){
            throw new UserNotInOrganizationException("This user does not have permission to edit this specific event");
        }
        event.setId(id);
        event.setOrganization(eventEntity.getOrganization());
        event.setActive(true);
        event.setParticipants(eventEntity.getParticipants());
        event.setUsers(eventEntity.getUsers());
        handleEmailsOnEdit(event);
        return eventRepository.save(event);
    }

    @Override
    public List<EventEntity> getEvents() {
        return eventRepository.findAll()
                .stream()
                .filter(EventEntity::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public EventEntity findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with ID: "+id+" not found"));
    }

    @Override
    public void assignParticipantsToEvent(Set<ParticipantEntity> participants, Long id) {
            EventEntity event = findEventById(id);
            event.setParticipants(participants);
            eventRepository.save(event);
    }

    @Override
    public void assignUsersToEvent(Set<UserInfo> users, Long id) {
        EventEntity event = findEventById(id);
        event.setUsers(users);
        eventRepository.save(event);
    }

    @Override
    public EventEntity cancelEvent(Long id) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with ID: "+id+" not found"));
        event.setActive(false);
        handleEmailsOnEdit(event);
        return eventRepository.save(event);
    }

    public void handleEmailsOnEdit(EventEntity event){
            Set<Observer> observers = event.getObservers();
            for (Observer observer : observers) {
                if (event.isActive()) {
                    String link;
                    if (observer.getClass().equals(UserInfo.class)) {
                        link = "http://localhost:8080/participant/cancel/event/" + observer.getId() + "?event" + event.getId();
                    } else {
                        link = "http://localhost:8080/participant/cancel/withoutAccount/event/" + observer.getId() + "?event" + event.getId();
                    }
                    emailSender.send(observer.getEmail(), EventService.buildEmail(observer.getEmail(), event, link));
                }
                else{
                    emailSender.send(observer.getEmail(),EventService.buildEmailCancelledEvent(observer.getEmail(), event));
                }
            }
    }
}
