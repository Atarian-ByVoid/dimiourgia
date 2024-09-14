package com.dimiourgia.integration;

import com.dimiourgia.helper.TokenGenerator;
import com.dimiourgia.integration.dto.IntegrationDTOValidation;
import com.dimiourgia.user.User;

public class IntegrationMapper {

    public static Integration toEntity(IntegrationDTOValidation request, User user) {
        Integration integration = new Integration();
        integration.setDescription(request.getDescription());
        integration.setKey(TokenGenerator.generateUniqueString());
        integration.setUser(user);
        return integration;

    }

}
