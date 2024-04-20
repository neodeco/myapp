package com.neodeco.login.controller;

import com.neodeco.login.model.AuthResponse;
import com.neodeco.login.model.RegisterRequest;
import com.neodeco.login.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
public class RegisterController {
    private final AuthServiceImpl service;

    @PostMapping()
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
}