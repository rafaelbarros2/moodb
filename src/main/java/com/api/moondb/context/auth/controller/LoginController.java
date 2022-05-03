package com.api.moondb.context.auth.controller;

import com.api.moondb.context.auth.model.request.LoginRequest;
import com.api.moondb.context.auth.model.response.LoginResponse;
import com.api.moondb.context.designermoodb.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse =  loginService.login(loginRequest);
        return ResponseEntity.status(loginResponse.getStatusCode()).body(loginResponse);
    }
}

