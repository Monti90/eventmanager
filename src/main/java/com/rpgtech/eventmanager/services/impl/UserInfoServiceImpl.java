package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.entities.UserInfo;
import com.rpgtech.eventmanager.repositories.UserInfoRepository;
import com.rpgtech.eventmanager.services.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;


    @Override
    public UserInfo createUserInfo(String discordName, String phoneNumber, String email) {
        UserInfo userInfo = new UserInfo(
                discordName,
                phoneNumber,
                email
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
                .findById(userInfoId)
                .get();
    }

    @Override
    public Set<UserInfo> findUsersInOrganization(OrganizationEntity organization) {
        return userInfoRepository.findUserInfosByOrganization(organization);
    }
}
