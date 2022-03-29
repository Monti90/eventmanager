package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class GameController {
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventEntity>> getEvents() {return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);}

    @GetMapping("/{id}")
    public ResponseEntity<EventEntity> getEventById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(eventService.findEventById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventEntity> addEvent(@RequestBody EventEntity event){
        return new ResponseEntity<>(eventService.addEvent(event), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventEntity> updateEvent(@PathVariable("id") Long id, @RequestBody EventEntity event){
        return new ResponseEntity<>(eventService.updateEvent(event, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
