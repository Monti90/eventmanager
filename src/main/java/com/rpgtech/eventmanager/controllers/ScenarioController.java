package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.ScenarioEntity;
import com.rpgtech.eventmanager.services.EventService;
import com.rpgtech.eventmanager.services.ScenarioService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scenario")
@AllArgsConstructor
public class ScenarioController {
    private ScenarioService scenarioService;

    @GetMapping
    public ResponseEntity<List<ScenarioEntity>> getAllUserScenarios(){
        return new ResponseEntity<>(scenarioService.getUserScenarios(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ScenarioEntity> createScenario(@RequestBody ScenarioEntity scenario, @RequestParam Long gameID){
        return new ResponseEntity<>(scenarioService.addScenario(scenario, gameID), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScenarioEntity> updateScenario(@RequestBody ScenarioEntity scenario, @PathVariable("id") Long id, @RequestParam Long gameID){
        return new ResponseEntity<>(scenarioService.updateScenario(scenario, id, gameID), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScenario(@PathVariable("id") Long id){
        scenarioService.deleteScenario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
