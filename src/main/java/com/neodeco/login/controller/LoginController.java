package com.neodeco.login.controller;

import com.neodeco.login.model.AuthRequest;
import com.neodeco.login.model.AuthResponse;
import com.neodeco.login.model.RegisterRequest;
import com.neodeco.login.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {
    private final AuthServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authtenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/greetings")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello there! You're authenticated!");
    }
}
