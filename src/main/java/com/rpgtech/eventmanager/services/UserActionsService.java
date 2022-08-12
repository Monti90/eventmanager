package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import org.aspectj.weaver.ast.Or;
import java.util.List;

public interface UserActionsService {

    public OrganizationEntity createOrganization(OrganizationEntity organization);
    public OrganizationEntity joinOrganization(Long id);
    public void leaveOrganization(Long id);
    public OrganizationEntity renameOrganization(Long id, String name);
    public List<OrganizationEntity> showOrganizations();
}
