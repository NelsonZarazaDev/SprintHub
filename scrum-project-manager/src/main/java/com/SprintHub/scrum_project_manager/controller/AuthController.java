package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.dto.AuthResponse;
import com.SprintHub.scrum_project_manager.dto.LoginRequest;
import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Users request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
