package com.insurance.service;

import java.util.List;
import com.insurance.model.Claim;

public interface ClaimService {

	// Create
	public Claim createClaim(Claim claim);

	// Get
	public List<Claim> getAllClaim();

	public Claim findById(Integer id);

	// Update
	public Claim updateClaim(Integer id, Claim claim) throws Exception;

	// Delete
	public void deleteClaim(Integer id);

}
