package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
