package com.insurance.auth.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.insurance.auth.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.secrete.key}")
	private String SECRETE_KEY;

	@Override
	public String generateAccessToken(UserDetails user) {
		return generateAccessToken(new HashMap<>(), user);
	}

	private String generateAccessToken(Map<String, Object> claims, UserDetails user) {
		return Jwts.builder().claims(claims).subject(user.getUsername())
				.expiration(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 60))
				.issuedAt(new Date(System.currentTimeMillis())).id(UUID.randomUUID().toString()).signWith(secreteKey())
				.compact();
	}

	// Extract Data

	public <T> T extractClaim(String accessToken, Function<Claims, T> claimResolver) {
		Claims claim = extractClaim(accessToken);
		return claimResolver.apply(claim);
	}

	private Claims extractClaim(String accessToken) {
		return Jwts.parser().verifyWith(secreteKey()).build().parseSignedClaims(accessToken).getPayload();
	}

	private SecretKey secreteKey() {
		byte[] key = Decoders.BASE64.decode(SECRETE_KEY);
		return Keys.hmacShaKeyFor(key);
	}

	@Override
	public String extractUsername(String accessToken) {
		return extractClaim(accessToken, Claims::getSubject);
	}

	@Override
	public Date extractExpiration(String accessToken) {
		return extractClaim(accessToken, Claims::getExpiration);
	}

	@Override
	public boolean isTokenValid(String accessToken, UserDetails userDetails) {
		return userDetails.getUsername().equals(extractUsername(accessToken)) && isTokenExpired(accessToken);
	}

	@Override
	public boolean isTokenExpired(String accessToken) {
		return extractExpiration(accessToken).after(new Date(System.currentTimeMillis()));
	}
}
