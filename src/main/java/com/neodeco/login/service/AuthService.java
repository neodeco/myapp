package com.neodeco.login.service;

import com.neodeco.login.model.AuthRequest;
import com.neodeco.login.model.AuthResponse;
import com.neodeco.login.model.RegisterRequest;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);

    AuthResponse register(RegisterRequest request);
}
