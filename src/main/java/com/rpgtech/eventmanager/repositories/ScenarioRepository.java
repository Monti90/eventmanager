package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.ScenarioEntity;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScenarioRepository  extends JpaRepository<ScenarioEntity, Long> {
    List<ScenarioEntity> findAllByUserInfo(UserInfo userInfo);
}
