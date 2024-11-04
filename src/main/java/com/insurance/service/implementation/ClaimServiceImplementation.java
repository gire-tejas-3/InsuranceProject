package com.insurance.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exceptions.ClaimNotFoundException;
import com.insurance.model.Claim;
import com.insurance.model.Status;
import com.insurance.repository.ClaimRepository;
import com.insurance.service.ClaimService;

@Service
public class ClaimServiceImplementation implements ClaimService {

	@Autowired
	private ClaimRepository claimRepository;

	@Override
	public Claim createClaim(Claim claim) {
		return claimRepository.save(claim);
	}

	@Override
	public List<Claim> getAllClaim(String status) {
		List<Claim> claimList;

		if (status != null) {
			claimList = claimRepository.findAll().stream().filter(claim -> claim.getStatus().equals(status)).toList();
		}
		claimList = claimRepository.findAll();
		return claimList;
	}

	@Override
	public Claim findById(Integer id) {
		return claimRepository.findById(id);
	}

	@Override
	public Claim updateClaim(Integer id, Claim claim) throws Exception {
		Claim exsistingClaim = claimRepository.findById(id);
		if (exsistingClaim == null) {
			throw new Exception("Claim is not found");
		}

		// update
		return claimRepository.save(claim);
	}

	@Override
	public void deleteClaim(Integer id) {
		claimRepository.deleteById(id);
	}

	@Override
	public Claim updateClaimStatus(Integer id, String status) throws Exception {

		Claim exsistingClaim = claimRepository.findById(id);
		if (exsistingClaim == null) {
			throw new ClaimNotFoundException("Claim is not found");
		}

		if (!exsistingClaim.getStatus().equals("INPROCESS")) {
			throw new Exception("Only claims with `INPROCESS` status can be updated");
		}

		exsistingClaim.setStatus(Status.valueOf(status));
		Claim updatedClaim = claimRepository.save(exsistingClaim);
		return updatedClaim;
	}
}
