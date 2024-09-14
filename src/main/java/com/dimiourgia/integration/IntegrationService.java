package com.dimiourgia.integration;

import org.springframework.stereotype.Service;

import com.dimiourgia.infrastructure.adapter.authentication.JwtService;
import com.dimiourgia.integration.domain.interfaces.IntegrationInterfaceRepository;
import com.dimiourgia.integration.dto.IntegrationDTOValidation;
import com.dimiourgia.user.User;
import com.dimiourgia.user.domain.interfaces.UserInterfaceRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Service
@Data
public class IntegrationService {

    private final IntegrationInterfaceRepository integrationInterfaceRepository;
    private final UserInterfaceRepository userInterfaceRepository;
    private final JwtService jwtService;

    public void create(IntegrationDTOValidation request, HttpServletRequest authentication) {
        String authToken = jwtService.extractToken(authentication);
        String userDocument = jwtService.getUserDocumentFromToken(authToken);
        User user = userInterfaceRepository.findByDocument(userDocument);
        Integration integration = IntegrationMapper.toEntity(request, user);
        integrationInterfaceRepository.save(integration);
    }

}
