package com.insurance.auth.service;

import com.insurance.auth.model.Refreshtoken;

public interface RefreshTokenService {

	Refreshtoken createRefreshToken(String username);

	boolean verifyRefreshToken(Refreshtoken refreshToken);

}
