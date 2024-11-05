package com.insurance.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.auth.model.Refreshtoken;

public interface RefreshTokenRespository extends JpaRepository<Refreshtoken, Integer> {

}
