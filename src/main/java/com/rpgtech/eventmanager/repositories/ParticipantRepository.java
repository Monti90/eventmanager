package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
