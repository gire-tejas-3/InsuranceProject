package com.insurance.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurance.model.Claim;
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
	public List<Claim> getAllClaim() {
		return claimRepository.findAll();
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
		return claimRepository.save(claim);
	}

	@Override
	public void deleteClaim(Integer id) {
		claimRepository.deleteById(id);
	}
}
