package com.insurance.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.insurance.model.Claim;
import com.insurance.service.ClaimService;

@RestController
@RequestMapping("/api/v1/claims")
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	@PostMapping
	public ResponseEntity<Claim> createClaim(Claim claim) {
		Claim createClaim = claimService.createClaim(claim);
		return new ResponseEntity<Claim>(createClaim, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Claim>> getAllClaim() {
		List<Claim> claimList = claimService.getAllClaim();
		return new ResponseEntity<List<Claim>>(claimList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Claim> findById(Integer id) {
		Claim claim = claimService.findById(id);
		return new ResponseEntity<Claim>(claim, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Claim> updateClaim(Integer id, Claim claim) throws Exception {
		Claim claimUpdate = claimService.updateClaim(id, claim);
		return new ResponseEntity<Claim>(claimUpdate, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClaim(Integer id) {
		claimService.deleteClaim(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}