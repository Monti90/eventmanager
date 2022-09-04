package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.dto.SessionCreateDTO;
import com.rpgtech.eventmanager.entities.SessionEntity;
import com.rpgtech.eventmanager.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<SessionEntity>> getAllSessions(){
        return new ResponseEntity<>(sessionService.getAllSessions(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionEntity> getSessionById(@PathVariable("id") Long id){
        return new ResponseEntity<>(sessionService.getSessionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SessionEntity> addSession(@RequestBody SessionCreateDTO session){
        return new ResponseEntity<>(sessionService.addSession(session),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionEntity> updateSession(@RequestBody SessionCreateDTO session, @PathVariable("id") Long id){
        return new ResponseEntity<>(sessionService.updateSession(session, id), HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<SessionEntity> cancelSession(@PathVariable("id") Long id){
        return new ResponseEntity<>(sessionService.cancelSession(id),HttpStatus.OK);
    }

    @GetMapping("/eventAppend/{id}/{eventId}")
    public ResponseEntity<SessionEntity> appendSessionToEvent(@PathVariable("id") Long id, @PathVariable("eventId") Long eventId){
        return new ResponseEntity<>(sessionService.appendSessionToEvent(id, eventId), HttpStatus.OK);
    }
}
