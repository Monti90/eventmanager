package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.GameEntity;
import com.rpgtech.eventmanager.exceptions.GameNotFoundException;
import com.rpgtech.eventmanager.repositories.GameRepository;
import com.rpgtech.eventmanager.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Override
    public GameEntity getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(()-> new GameNotFoundException("Game with id:"+ id+" not found"));
    }

    @Override
    public List<GameEntity> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public GameEntity addGame(String gameName) {
        return gameRepository.save(new GameEntity(gameName));
    }

    @Override
    public GameEntity updateGame(String gameName, Long id) {
        if(gameRepository.findById(id).isPresent()){
            GameEntity game = new GameEntity(gameName);
            game.setId(id);
            return gameRepository.save(game);
        }
        else{
            throw new GameNotFoundException("Game with id:"+ id+" not found");
        }
    }

    @Override
    public void removeGame(Long id) {
        if(gameRepository.findById(id).isPresent())
        {
            gameRepository.deleteById(id);
        }
        else{
            throw new GameNotFoundException("Game with id:"+ id+" not found");
        }
    }
}
