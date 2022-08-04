package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.entities.UserInfo;
import com.rpgtech.eventmanager.exceptions.OrganizationNotFoundException;
import com.rpgtech.eventmanager.exceptions.UserInfoNotFoundException;
import com.rpgtech.eventmanager.repositories.OrganizationRepository;
import com.rpgtech.eventmanager.repositories.UserInfoRepository;
import com.rpgtech.eventmanager.services.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;


    @Override
    public UserInfo createUserInfo(String discordName, String phoneNumber) {
        UserInfo userInfo = new UserInfo(
                discordName,
                phoneNumber
        );
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
         return userInfoRepository.save(userInfo);
        }

    @Override
    public UserInfo findUserInfoById(Long id) {
        return userInfoRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public boolean isUserInOrganization() {
        UserInfo userInfo =  currentlyLoggedUser();
        return userInfo.getOrganization() == null;
    }

    @Override
    public UserInfo currentlyLoggedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userInfoId = user
                .getUserInfo()
                .getId();
        return userInfoRepository
                .getById(userInfoId);
    }

    @Override
    public Set<UserInfo> findUsersInOrganization(OrganizationEntity organization) {
        return userInfoRepository.findUserInfosByOrganization(organization);
    }
}
