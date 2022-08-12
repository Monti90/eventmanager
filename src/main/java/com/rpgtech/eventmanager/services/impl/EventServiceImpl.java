package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import com.rpgtech.eventmanager.exceptions.BadTimeSpanException;
import com.rpgtech.eventmanager.exceptions.EventNotFoundException;
import com.rpgtech.eventmanager.exceptions.UserNotInOrganizationException;
import com.rpgtech.eventmanager.repositories.EventRepository;
import com.rpgtech.eventmanager.services.EventService;
import com.rpgtech.eventmanager.services.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserInfoService userInfoService;

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
        event.setActive(true);
        return eventRepository.save(event);
    }

    @Override
    public List<EventEntity> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public EventEntity findEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with ID: "+id+" not found"));
    }

    @Override
    public void deleteEvent(Long id) {
    eventRepository.delete(findEventById(id));
    }
}
