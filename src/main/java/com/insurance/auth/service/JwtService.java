package com.insurance.auth.service;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	String extractUsername(String accessToken);

	Date extractExpiration(String accessToken);

	String generateAccessToken(UserDetails user);

	boolean isTokenValid(String accessToken, UserDetails userDetails);

	boolean isTokenExpired(String accessToken);

}
