package com.rpgtech.eventmanager.services;


import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.entities.UserInfo;

public interface UserInfoService {

    public UserInfo createUserInfo(String discordName, String phoneNumber);
    public UserInfo updateUserInfo(UserInfo userInfo);
    public UserInfo findUserInfoById(Long id);
    public boolean isUserInOrganization();
    public UserInfo currentlyLoggedUser();
}
