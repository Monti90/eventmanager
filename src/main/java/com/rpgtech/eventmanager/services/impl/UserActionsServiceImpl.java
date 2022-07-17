package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.entities.User;
import com.rpgtech.eventmanager.entities.UserInfo;
import com.rpgtech.eventmanager.exceptions.UserAlreadyInOrganizationException;
import com.rpgtech.eventmanager.services.OrganizationService;
import com.rpgtech.eventmanager.services.UserActionsService;
import com.rpgtech.eventmanager.services.UserInfoService;
import com.rpgtech.eventmanager.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserActionsServiceImpl implements UserActionsService {

    private final OrganizationService organizationService;
    private final UserInfoService userInfoService;

    @Transactional
    @Override
    public OrganizationEntity createOrganization(OrganizationEntity organization) {
        if(userInfoService.isUserInOrganization())
        {
            Long orgId = organizationService.addOrganization(organization.getOrganizationName())
                                            .getId();
            OrganizationEntity organizationEntity = organizationService.findOrganizationById(orgId);
            UserInfo userInfo = userInfoService.currentlyLoggedUser();
            userInfo.setOrganization(organizationEntity);
            userInfoService.updateUserInfo(userInfo);
            return organizationEntity;
        }
        throw new UserAlreadyInOrganizationException("User already belongs to an organization!");
    }

    @Override
    public OrganizationEntity joinOrganization(Long id) {
        if(userInfoService.isUserInOrganization())
        {
            OrganizationEntity organizationEntity = organizationService.findOrganizationById(id);
            UserInfo userInfo = userInfoService.currentlyLoggedUser();
            userInfo.setOrganization(organizationEntity);
            userInfoService.updateUserInfo(userInfo);
            return organizationEntity;
        }
        throw new UserAlreadyInOrganizationException("User already belongs to an organization!");
    }
}
