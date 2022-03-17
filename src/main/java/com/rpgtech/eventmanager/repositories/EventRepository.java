package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
