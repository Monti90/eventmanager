package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.services.UserActionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
public class OrganizationController {

    private final UserActionsService userActionsService;

    @PostMapping
    public OrganizationEntity addOrganization(@RequestBody OrganizationEntity organization){
        return userActionsService.createOrganization(organization);
    }
}
