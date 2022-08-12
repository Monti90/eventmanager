package com.rpgtech.eventmanager.controllers;

import com.rpgtech.eventmanager.entities.GameEntity;
import com.rpgtech.eventmanager.services.GameService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {
    private final GameService gameService;


    @GetMapping("/{id}")
    public ResponseEntity<GameEntity> getGame(@PathVariable("id") Long id){
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<GameEntity>> getAllGames(){
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GameEntity> addGame(@RequestParam String gameName){
        return new ResponseEntity<>(gameService.addGame(gameName), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameEntity> updateGame(@RequestParam String gameName, @PathVariable("id") Long id){
        return new ResponseEntity<>(gameService.updateGame(gameName, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeGame(@PathVariable("id") Long id){
        gameService.removeGame(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

}
