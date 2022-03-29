package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.EventEntity;

import java.util.List;

public interface ScenarioService {
    EventEntity addEvent(EventEntity event);
    EventEntity updateEvent(EventEntity event, Long id);
    List<EventEntity> getEvents();
    EventEntity findEventById(Long id);
    void deleteEvent(Long id);
}
