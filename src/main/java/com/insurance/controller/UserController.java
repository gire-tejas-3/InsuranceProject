package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.model.Policy;
import com.insurance.service.ClaimService;
import com.insurance.service.PolicyService;
import com.insurance.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private ClaimService claimService;

	// Policy
	@GetMapping("/policies")
	public ResponseEntity<List<Policy>> getAllPolicies(
			@RequestParam(required = false, defaultValue = "AVAILABLE") String status) {
		List<Policy> policies;
		if (status != null) {
			policies = policyService.getAllPolicy(status).stream().filter(policy -> policy.getStatus().equals(status))
					.toList();
		}
		policies = policyService.getAllPolicy(status);
		return new ResponseEntity<List<Policy>>(policies, HttpStatus.OK);
	}

}
