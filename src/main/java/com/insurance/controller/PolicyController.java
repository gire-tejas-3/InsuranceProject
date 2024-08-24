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
import org.springframework.web.bind.annotation.RestController;
import com.insurance.model.Policy;
import com.insurance.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/policies")
public class PolicyController {

	@Autowired
	private PolicyService policyService;

	@PostMapping
	public ResponseEntity<Policy> createPolicy(@Valid @RequestBody	Policy policy) {
		Policy createdPolicy = policyService.createPolicy(policy);
		return new ResponseEntity<Policy>(createdPolicy, HttpStatus.CREATED);
	}

	@GetMapping
	private ResponseEntity<List<Policy>> getAllPolicy(String status) {
		List<Policy> policyList = policyService.getAllPolicy(status);
		return new ResponseEntity<List<Policy>>(policyList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Policy> findById(@PathVariable int id) {
		Policy policy = policyService.findById(id);
		return new ResponseEntity<Policy>(policy, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Policy> updatePolicy(@Valid @PathVariable int id, @RequestBody Policy policy) throws Exception {
		Policy updatedPolicy = policyService.updatePolicy(id, policy);
		return new ResponseEntity<Policy>(updatedPolicy, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePolicy(@PathVariable int id) {
		policyService.deletePolicy(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
