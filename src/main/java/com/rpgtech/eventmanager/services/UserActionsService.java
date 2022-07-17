package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import org.aspectj.weaver.ast.Or;

public interface UserActionsService {

    public OrganizationEntity createOrganization(OrganizationEntity organization);
    public OrganizationEntity joinOrganization(Long id);
}
