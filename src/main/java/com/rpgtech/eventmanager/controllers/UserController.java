package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.requests.RegistrationRequest;
import com.rpgtech.eventmanager.services.EventService;
import com.rpgtech.eventmanager.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private RegistrationService registrationService;

    @PostMapping("")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){ return registrationService.confirmToken(token);}
}
