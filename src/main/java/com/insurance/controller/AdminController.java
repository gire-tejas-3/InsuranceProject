package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.insurance.model.Claim;
import com.insurance.model.Policy;
import com.insurance.model.User;
import com.insurance.service.ClaimService;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin")

public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private ClaimService claimService;

	// Policy
	@GetMapping("/policies")
	public ResponseEntity<List<Policy>> getAllPolicies(@RequestParam(required = false) String status) {
		List<Policy> policies = policyService.getAllPolicy(status);
		return new ResponseEntity<List<Policy>>(policies, HttpStatus.OK);
	}

	@GetMapping("/policy/{id}")
	public ResponseEntity<Policy> getPolicyById(@PathVariable Integer id) {
		Policy policy = policyService.findById(id);
		return new ResponseEntity<Policy>(policy, HttpStatus.OK);
	}

	@PostMapping("/policy")
	public ResponseEntity<Policy> createPolicy(@Valid @RequestBody Policy policy) {
		Policy createdPolicy = policyService.createPolicy(policy);
		return new ResponseEntity<Policy>(createdPolicy, HttpStatus.CREATED);
	}

	@PutMapping("/policy/{id}")
	public ResponseEntity<Policy> updatePolicy(@PathVariable Integer id, @Valid @RequestBody Policy policy)
			throws Exception {
		Policy updatedPolicy = policyService.updatePolicy(id, policy);
		return new ResponseEntity<Policy>(updatedPolicy, HttpStatus.OK);
	}

	@PutMapping("/policy/{status}/{id}")
	public ResponseEntity<Policy> updatePolicyStatus(@PathVariable Integer id, @PathVariable String status)
			throws Exception {
		Policy updatedPolicy = policyService.updatePolicyStatus(id, status);
		return new ResponseEntity<Policy>(updatedPolicy, HttpStatus.OK);
	}

	@DeleteMapping("/policy/{id}")
	public ResponseEntity<Void> deletePolicy(@PathVariable Integer id) {
		policyService.deletePolicy(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// User

	@GetMapping("/user/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		User user = userService.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK); // 200 for Ok
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String role) {
		List<User> usersList = userService.getAllUser(role);
		return new ResponseEntity<List<User>>(usersList, HttpStatus.OK); // 200 for Ok
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		// throw
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// Claim

	@PutMapping("/claim/{id}")
	public ResponseEntity<Claim> updateClaimStatus(@PathVariable int id, @RequestParam(required = false) String status)
			throws Exception {
		Claim updatedClaim = claimService.updateClaimStatus(id, status);
		return new ResponseEntity<Claim>(updatedClaim, HttpStatus.OK);
	}

	@GetMapping("/claims")
	public ResponseEntity<List<Claim>> getAllClaim(@RequestParam(required = false) String status) {
		List<Claim> claims = claimService.getAllClaim(status);
		return new ResponseEntity<List<Claim>>(claims, HttpStatus.OK);
	}

}
