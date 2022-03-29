package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.exceptions.EventNotFoundException;
import com.rpgtech.eventmanager.repositories.EventRepository;
import com.rpgtech.eventmanager.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public EventEntity addEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public EventEntity updateEvent(EventEntity event, Long id) {
        //TODO - Check if that works
        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with ID: "+id+" not found"));
        event.setId(id);
        return eventRepository.save(event);
    }

    @Override
    public List<EventEntity> getEvents() {
        return eventRepository.findAll().stream()
                .collect(Collectors.toList());
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
