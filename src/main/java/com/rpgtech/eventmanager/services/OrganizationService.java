package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.OrganizationEntity;

import java.util.List;

public interface OrganizationService {

    public OrganizationEntity addOrganization(String organizationName);
    public OrganizationEntity findOrganizationById(Long id);
    public OrganizationEntity renameOrganization(OrganizationEntity organization);
    public void deleteOrganization(OrganizationEntity organization);

    List<OrganizationEntity> showOrganizations();
}
