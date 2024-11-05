package com.insurance.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.auth.service.AuthService;
import com.insurance.auth.utils.AuthResponse;
import com.insurance.auth.utils.LoginRequest;
import com.insurance.auth.utils.RegisterRequest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	@Operation(description = "New User Registration")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
		return new ResponseEntity<AuthResponse>(authService.register(req), HttpStatus.CREATED);
	}

	@Operation(description = "New User Sign in")
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
		return new ResponseEntity<AuthResponse>(authService.login(req), HttpStatus.OK);
	}

	@PostMapping("/logout")
	@Operation(description = "Logout User")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response,
			@RequestHeader(value = "Authorization", required = false) String header) {
 
		var authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return new ResponseEntity<String>("{\"message\":\"logout successfull\"}", HttpStatus.OK);
	}

}
