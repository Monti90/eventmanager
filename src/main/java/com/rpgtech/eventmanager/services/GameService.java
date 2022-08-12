package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.GameEntity;

import java.util.List;

public interface GameService {

    GameEntity getGameById(Long id);
    List<GameEntity> getAllGames();
    GameEntity addGame(String gameName);
    GameEntity updateGame(String gameName, Long id);
    void removeGame(Long id);
}
