package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.ScenarioEntity;
import com.rpgtech.eventmanager.exceptions.ScenarioNotFoundException;
import com.rpgtech.eventmanager.repositories.GameRepository;
import com.rpgtech.eventmanager.repositories.ScenarioRepository;
import com.rpgtech.eventmanager.services.GameService;
import com.rpgtech.eventmanager.services.ScenarioService;
import com.rpgtech.eventmanager.services.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class ScenarioServiceImpl implements ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final UserInfoService userInfoService;
    private final GameService gameService;

    @Override
    public ScenarioEntity addScenario(ScenarioEntity scenario, Long gameID) {
        scenario.setGame(gameService.getGameById(gameID));
        scenario.setUserInfo(userInfoService.currentlyLoggedUser());
        if(scenario.getDurationMinutes() == null){
            scenario.setDurationMinutes(0l);
        }
        return scenarioRepository.save(scenario);
    }

    @Override
    public ScenarioEntity updateScenario(ScenarioEntity scenario, Long id, Long gameID) {
        ScenarioEntity scenarioEntity = scenarioRepository.findById(id)
                .orElseThrow(()-> new ScenarioNotFoundException("Scenario with ID:"+ id +" not found."));
        scenarioEntity = scenario;
        scenarioEntity.setGame(gameService.getGameById(gameID));
        scenarioEntity.setScenarioID(id);
        scenarioEntity.setUserInfo(userInfoService.currentlyLoggedUser());
        if(scenario.getDurationMinutes() == null){
            scenario.setDurationMinutes(0l);
        }
        return scenarioRepository.save(scenarioEntity);
    }

    @Override
    public void deleteScenario(Long id) {
        scenarioRepository.deleteById(id);
    }

    @Override
    public List<ScenarioEntity> getUserScenarios() {
        return scenarioRepository.findAllByUserInfo(userInfoService.currentlyLoggedUser());
    }

    @Override
    public ScenarioEntity getScenarioById(Long id) {
        return scenarioRepository.findById(id)
                .orElseThrow(()-> new ScenarioNotFoundException("Scenario with ID:"+ id +" not found."));
    }
}
