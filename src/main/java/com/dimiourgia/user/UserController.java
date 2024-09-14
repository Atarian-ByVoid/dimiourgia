package com.dimiourgia.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimiourgia.user.dto.UserDTOValidation;
import com.dimiourgia.user.dto.UserDTOValidation.UserValidation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/dimiourgia/api/user")
@Tag(name = "User", description = "Operations related to user management")
public class UserController {

    private final UsuarioService usuarioService;

    /**
     * Register a new user.
     *
     * @param request UserDTOValidation containing the user data to be created.
     */
    @Operation(summary = "Register a new user", description = "Creates a new user based on the provided request body")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("register")
    public void register(
            @Validated({ UserValidation.Create.class }) @RequestBody UserDTOValidation request) {
        usuarioService.create(request);
    }
}
