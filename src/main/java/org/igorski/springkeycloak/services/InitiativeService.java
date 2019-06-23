package org.igorski.springkeycloak.services;

import org.igorski.springkeycloak.model.DataException;
import org.igorski.springkeycloak.model.Initiative;
import org.igorski.springkeycloak.repositories.InitiativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitiativeService {

    private InitiativeRepository initiativeRepository;

    @Autowired
    public InitiativeService(InitiativeRepository initiativeRepository) {
        this.initiativeRepository = initiativeRepository;
    }

    public Initiative createInitiative(Initiative initiative) {
        return initiativeRepository.save(initiative);
    }

    public Initiative getInitiative(Long initiativeId) throws DataException {
        return initiativeRepository.findById(initiativeId).orElseThrow(() -> new DataException("No Initiative found."));
    }
}
