package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.services.UserActionsService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
public class OrganizationController {

    private final UserActionsService userActionsService;

    @GetMapping
    public ResponseEntity<List<OrganizationEntity>> getOrganizations(){
        return new ResponseEntity<>(userActionsService.showOrganizations(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrganizationEntity> addOrganization(@RequestBody OrganizationEntity organization){
        return new ResponseEntity<>(userActionsService.createOrganization(organization), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizationEntity> joinOrganization(@PathVariable("id") Long id){
        return new ResponseEntity<>(userActionsService.joinOrganization(id), HttpStatus.OK);
    }

    @PutMapping("/rename/{id}")
    public ResponseEntity<OrganizationEntity> renameOrganization(@PathVariable("id") Long id, @RequestParam String name){
        return new ResponseEntity<>(userActionsService.renameOrganization(id, name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> leaveOrganization(@PathVariable("id") Long id){
        userActionsService.leaveOrganization(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
