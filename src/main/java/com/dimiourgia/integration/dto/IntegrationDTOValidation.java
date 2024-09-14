package com.dimiourgia.integration.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class IntegrationDTOValidation {

    @Size(max = 500, message = "Description must be less than or equal to 500 characters", groups = IntegrationValidation.Create.class)
    private String description;

    public class IntegrationValidation {

        public interface Create {

        }

    }

}
