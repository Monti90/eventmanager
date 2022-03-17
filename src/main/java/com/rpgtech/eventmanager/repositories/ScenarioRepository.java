package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.ScenarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository  extends JpaRepository<ScenarioEntity, Long> {
}
