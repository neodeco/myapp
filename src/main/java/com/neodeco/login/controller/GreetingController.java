package com.neodeco.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth/greetings")
@RequiredArgsConstructor
public class GreetingController {

    @GetMapping()
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello there! You're secure.");
    }
}
