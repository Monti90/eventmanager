package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.services.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participant")
@AllArgsConstructor
public class ParticipantController {

    ParticipantService participantService;

    @PostMapping("/withoutAccount/event/{id}")
    public ResponseEntity<String> assignUnregisteredToEvent(@RequestBody ParticipantEntity participant, @PathVariable("id") Long id) {
            return new ResponseEntity<>(participantService.assignUnregisteredToEvent(participant, id),HttpStatus.CREATED);
    }

    @PostMapping("/withoutAccount/session/{id}")
    public ResponseEntity<String> assignUnregisteredToSession(@RequestBody ParticipantEntity participant, @PathVariable("id")  Long id) {
        return new ResponseEntity<>(participantService.assignUnregisteredToSession(participant, id),HttpStatus.CREATED);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<String> assignLoggedUserToEvent(@PathVariable("id") Long id){
        return new ResponseEntity<>(participantService.assignUserToEvent(id),HttpStatus.OK);
    }

    @PutMapping("/session/{id}")
    public ResponseEntity<String> assignLoggedUserToSession(@PathVariable("id") Long id){
        return new ResponseEntity<>(participantService.assignUserToSession(id),HttpStatus.OK);
    }

    @GetMapping("/cancel/session/{id}")
    public ResponseEntity<String> resignLoggedUserSession(@PathVariable("id") Long id, @RequestParam("session") Long sessionId){
        return new ResponseEntity<>(participantService.resignLoggedUserSession(sessionId, id),HttpStatus.OK);
    }

    @GetMapping("/cancel/withoutAccount/session/{id}")
    public ResponseEntity<String> resignUnregisteredSession(@PathVariable("id") Long id, @RequestParam("session") Long sessionId){
        return new ResponseEntity<>(participantService.resignUnregisteredSession(id, sessionId),HttpStatus.OK);
    }

    @GetMapping("/cancel/event/{id}")
    public ResponseEntity<String> resignLoggedUserEvent(@PathVariable("id") Long id, @RequestParam("event") Long eventId){
        return new ResponseEntity<>(participantService.resignLoggedUserEvent(eventId, id),HttpStatus.OK);
    }

    @GetMapping("/cancel/withoutAccount/event/{id}")
    public ResponseEntity<String> resignUnregisteredEvent(@PathVariable("id") Long id, @RequestParam("event") Long eventId){
        return new ResponseEntity<>(participantService.resignUnregisteredEvent(id, eventId),HttpStatus.OK);
    }



}
