package com.rpgtech.eventmanager.repositories;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

        Set<UserInfo> findUserInfosByOrganization(OrganizationEntity organization);
}
