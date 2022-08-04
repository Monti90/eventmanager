package com.rpgtech.eventmanager.services;

import com.rpgtech.eventmanager.entities.OrganizationEntity;

public interface OrganizationService {

    public OrganizationEntity addOrganization(String organizationName);
    public OrganizationEntity findOrganizationById(Long id);
    public void deleteOrganization(OrganizationEntity organization);
}
