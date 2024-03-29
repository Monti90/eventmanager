package com.rpgtech.eventmanager.services.impl;

import com.rpgtech.eventmanager.entities.OrganizationEntity;
import com.rpgtech.eventmanager.exceptions.OrganizationNotFoundException;
import com.rpgtech.eventmanager.repositories.OrganizationRepository;
import com.rpgtech.eventmanager.services.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationEntity addOrganization(String organizationName) {
        OrganizationEntity organization = new OrganizationEntity(organizationName);
        return organizationRepository.save(organization);
    }

    @Override
    public OrganizationEntity findOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with id:"+id+" not found"));
    }

    @Override
    public OrganizationEntity renameOrganization(OrganizationEntity organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public void deleteOrganization(OrganizationEntity organization) {
        organizationRepository.delete(organization);
    }

    @Override
    public List<OrganizationEntity> showOrganizations() {return organizationRepository.findAll();}
}
