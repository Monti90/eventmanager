package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.EventEntity;
import com.rpgtech.eventmanager.entities.ScenarioEntity;

import java.util.List;

public interface ScenarioService {
    ScenarioEntity addScenario(ScenarioEntity scenario, Long gameID);
    ScenarioEntity updateScenario(ScenarioEntity scenario, Long id, Long gameID);
    void deleteScenario(Long id);
    List<ScenarioEntity> getUserScenarios();
    ScenarioEntity getScenarioById(Long id);
}
