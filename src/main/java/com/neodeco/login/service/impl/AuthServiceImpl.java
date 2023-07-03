package com.neodeco.login.service.impl;

import com.neodeco.login.config.JwtAuthSevice;
import com.neodeco.login.domain.UserEntity;
import com.neodeco.login.domain.enums.Role;
import com.neodeco.login.model.AuthRequest;
import com.neodeco.login.model.AuthResponse;
import com.neodeco.login.model.RegisterRequest;
import com.neodeco.login.repository.UserRepository;
import com.neodeco.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtAuthSevice jwtAuthSevice;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepository repository, PasswordEncoder encoder,
                           JwtAuthSevice jwtAuthSevice,
                           AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtAuthSevice = jwtAuthSevice;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail()
                , request.getPassword()));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtAuthSevice.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = UserEntity.builder()
                .username(request.getUserName())
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtAuthSevice.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
