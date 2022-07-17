package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
