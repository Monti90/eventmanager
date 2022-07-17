package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
}
