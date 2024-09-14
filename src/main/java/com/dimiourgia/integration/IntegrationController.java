package com.dimiourgia.integration;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimiourgia.integration.dto.IntegrationDTOValidation;
import com.dimiourgia.integration.dto.IntegrationDTOValidation.IntegrationValidation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/dimiourgia/api/integration")
@Tag(name = "Integration", description = "Operations related to integrations")
public class IntegrationController {

    private final IntegrationService integrationService;

    /**
     * Register a new integration.
     *
     * @param request IntegrationDTOValidation containing the integration data to be
     *                created.
     * @return ResponseEntity indicating the result of the operation.
     */
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Register a new integration", description = "Creates a new integration based on the provided request body.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Integration registered successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("generate")
    public void generateIntegration(
            HttpServletRequest authentication,
            @Validated({ IntegrationValidation.Create.class }) @RequestBody IntegrationDTOValidation request) {
        integrationService.create(request, authentication);
    }
}
