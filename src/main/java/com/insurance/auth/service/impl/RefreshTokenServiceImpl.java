
package com.insurance.auth.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.auth.model.Refreshtoken;
import com.insurance.auth.repository.RefreshTokenRespository;
import com.insurance.auth.service.RefreshTokenService;
import com.insurance.exceptions.UserNotFoundException;
import com.insurance.model.User;
import com.insurance.repository.UserRepository;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private RefreshTokenRespository refreshTokenRespository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Refreshtoken createRefreshToken(String username) {
		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new UserNotFoundException("User Not found with username: " + username);
		}

		Refreshtoken refreshToken = user.getRefreshToken();

		if (refreshToken != null && verifyRefreshToken(refreshToken) == false) {
			refreshTokenRespository.delete(refreshToken);
		} else {
			refreshToken = new Refreshtoken();
			refreshToken.setIssuedAt(new Date(System.currentTimeMillis()));
			refreshToken.setToken(UUID.randomUUID().toString());
			refreshToken.setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 60));
			refreshToken.setUser(user);
			refreshTokenRespository.save(refreshToken);
		}

		return refreshToken;
	}

	@Override
	public boolean verifyRefreshToken(Refreshtoken refreshToken) {
		if (refreshToken.getExpiration().compareTo(new Date(System.currentTimeMillis())) > 0) {
			return false;
		}

		return true;
	}

}
