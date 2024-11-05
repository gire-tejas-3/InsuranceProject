package com.insurance.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insurance.auth.service.AuthService;
import com.insurance.auth.service.JwtService;
import com.insurance.auth.service.RefreshTokenService;
import com.insurance.auth.utils.AuthResponse;
import com.insurance.auth.utils.LoginRequest;
import com.insurance.auth.utils.RegisterRequest;
import com.insurance.exceptions.UserNotFoundException;
import com.insurance.model.User;
import com.insurance.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public AuthResponse register(RegisterRequest req) {
		User user = new User();
		user.setName(req.getName());
		user.setUsername(req.getUsername());
		user.setEmail(req.getEmail());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setMobileNo(req.getMobileNo());
		user.setBirthDate(req.getBirthDate());
		user.setGender(req.getGender());
		user.setMaritalStatus(req.getMaritalStatus());
		user.setRole(req.getRole());
		user.setActive(true);

		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = refreshTokenService.createRefreshToken(req.getUsername()).toString();

		userRepository.save(user);
		return new AuthResponse(accessToken, refreshToken);
	}

	@Override
	public AuthResponse login(LoginRequest req) {
		User user = userRepository.findByUsername(req.getUsername());

		if (user == null) {
			throw new UserNotFoundException("User not Found with username: " + req.getUsername());
		}

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities()));

		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = refreshTokenService.createRefreshToken(user.getUsername()).toString();

		return new AuthResponse(accessToken, refreshToken);
	}
}
