package com.insurance.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

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
		userRepository.save(user);

		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = refreshTokenService.createRefreshToken(req.getUsername()).getToken();

		logger.info("User Registration Successfull: " + user.getUsername());
		return new AuthResponse(accessToken, refreshToken);
	}

	@Override
	public AuthResponse login(LoginRequest req) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

		User user = userRepository.findByUsername(req.getUsername());

		if (user == null) {
			logger.error("User not Found with username: " + req.getUsername());
			throw new UserNotFoundException("User not Found with username: " + req.getUsername());
		}

		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = refreshTokenService.createRefreshToken(user.getUsername()).getToken();
		logger.info("Access Token" + accessToken);
		logger.info("Successfully Logged In!");
		return new AuthResponse(accessToken, refreshToken);
	}
}
