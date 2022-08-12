package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.requests.RegistrationRequest;
import com.rpgtech.eventmanager.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private RegistrationService registrationService;

    @PostMapping("")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        return new ResponseEntity<>(registrationService.register(request), HttpStatus.CREATED);
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token){
        return new ResponseEntity<>(registrationService.confirmToken(token),HttpStatus.OK);
    }
}
