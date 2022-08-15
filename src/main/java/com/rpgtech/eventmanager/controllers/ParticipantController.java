package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.ParticipantEntity;
import com.rpgtech.eventmanager.services.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    ParticipantService participantService;

    @PostMapping("/withoutAccount/event")
    public ResponseEntity<String> assignUnregisteredToEvent(@RequestBody ParticipantEntity participant, @PathVariable Long id) {
            return new ResponseEntity<>(participantService.assignUnregisteredToEvent(participant, id),HttpStatus.CREATED);
    }

    @PostMapping("/withoutAccount/session")
    public ResponseEntity<String> assignUnregisteredToSession(@RequestBody ParticipantEntity participant, @PathVariable Long id) {
        return new ResponseEntity<>(participantService.assignUnregisteredToSession(participant, id),HttpStatus.CREATED);
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<String> assignLoggedUserToEvent(@RequestParam("id") Long id){
        return new ResponseEntity<>(participantService.assignUserToEvent(id),HttpStatus.OK);
    }

    @PutMapping("/session/{id}")
    public ResponseEntity<String> assignLoggedUserToSession(@RequestParam("id") Long id){
        return new ResponseEntity<>(participantService.assignUserToSession(id),HttpStatus.OK);
    }



}
