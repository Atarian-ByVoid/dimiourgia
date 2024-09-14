package com.dimiourgia.infrastructure.adapter.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dimiourgia.infrastructure.adapter.authentication.records.AccessToken;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AccessToken> login() {
        AccessToken response = authService.login();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}