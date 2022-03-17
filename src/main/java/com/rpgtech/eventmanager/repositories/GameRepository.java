package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
}
