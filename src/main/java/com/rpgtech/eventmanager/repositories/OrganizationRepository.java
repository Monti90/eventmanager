package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
}
