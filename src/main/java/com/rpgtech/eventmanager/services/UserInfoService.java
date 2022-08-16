package com.rpgtech.eventmanager.services;


import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.UserInfo;

import java.util.Set;

public interface UserInfoService {

    public UserInfo createUserInfo(String discordName, String phoneNumber, String email);
    public UserInfo updateUserInfo(UserInfo userInfo);
    public UserInfo findUserInfoById(Long id);
    public boolean isUserInOrganization();
    public UserInfo currentlyLoggedUser();
    public Set<UserInfo> findUsersInOrganization(OrganizationEntity organization);
}
