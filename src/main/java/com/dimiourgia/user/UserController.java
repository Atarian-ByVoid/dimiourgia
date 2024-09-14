package com.dimiourgia.user;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimiourgia.user.dto.UserDTOValidation;
import com.dimiourgia.user.dto.UserDTOValidation.UserValidation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "user")
public class UserController {

    private final UsuarioService usuarioService;

    @PostMapping("register")
    public void register(
            @Validated({ UserValidation.Create.class }) @RequestBody UserDTOValidation request) {

        usuarioService.create(request);

    }

}
