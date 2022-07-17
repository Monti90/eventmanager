package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class GameController {
    private EventService eventService;

}
