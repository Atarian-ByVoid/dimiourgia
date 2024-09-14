package com.dimiourgia.integration.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dimiourgia.integration.Integration;

public interface IntegrationRepository extends JpaRepository<Integration, UUID> {

}
