package com.neodeco.login.controller;

import com.neodeco.login.model.AuthRequest;
import com.neodeco.login.model.AuthResponse;
import com.neodeco.login.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/authenticate")
@RequiredArgsConstructor
public class LoginController {
    private final AuthServiceImpl service;

    @PostMapping()
    public ResponseEntity<AuthResponse> authtenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
