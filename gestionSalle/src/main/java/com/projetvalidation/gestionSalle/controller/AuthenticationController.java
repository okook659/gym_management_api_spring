package com.projetvalidation.gestionSalle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetvalidation.gestionSalle.entity.AuthenticationResponse;
import com.projetvalidation.gestionSalle.entity.User;
import com.projetvalidation.gestionSalle.service.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.register((request)));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
        @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
