package com.dimiourgia.integration.domain;

import org.springframework.stereotype.Repository;

import com.dimiourgia.integration.Integration;
import com.dimiourgia.integration.domain.interfaces.IntegrationInterfaceRepository;
import com.dimiourgia.integration.domain.repository.IntegrationRepository;

import lombok.Data;

@Repository
@Data
public class JpaIntegrationRepository implements IntegrationInterfaceRepository {

    private final IntegrationRepository integrationRepository;

    @Override
    public Integration save(Integration integration) {
        return integrationRepository.save(integration);
    }

}
