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
public class ScenarioController {
    private EventService eventService;

}
