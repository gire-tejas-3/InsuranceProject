package com.insurance.auth.service;

import com.insurance.auth.utils.AuthResponse;
import com.insurance.auth.utils.LoginRequest;
import com.insurance.auth.utils.RegisterRequest;

public interface AuthService {

	AuthResponse register(RegisterRequest req);

	AuthResponse login(LoginRequest req);
}
