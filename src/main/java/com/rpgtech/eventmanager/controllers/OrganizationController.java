package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.services.UserActionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
public class OrganizationController {

    private final UserActionsService userActionsService;

    @PostMapping
    public OrganizationEntity addOrganization(@RequestBody OrganizationEntity organization){
        return userActionsService.createOrganization(organization);
    }

    @PutMapping("/{id}")
    public OrganizationEntity joinOrganization(@PathVariable("id") Long id){
        return userActionsService.joinOrganization(id);
    }
}
