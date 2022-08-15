package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.EventObserver;
import com.rpgtech.eventmanager.entities.Observer;
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
    public ResponseEntity<ParticipantEntity> assignUnregisteredToEvent(@RequestBody ParticipantEntity participant, @PathVariable Long id) {
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/withoutAccount/session")
    public ResponseEntity<ParticipantEntity> assignUnregisteredToSession(@RequestBody ParticipantEntity participant, @PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
