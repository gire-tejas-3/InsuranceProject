package com.insurance.service;

import java.util.List;
import com.insurance.model.Policy;

public interface PolicyService {

	// Create
	public Policy createPolicy(Policy policy);

	// Get
	public List<Policy> getAllPolicy();

	public Policy findById(int id);

	// Update
	public Policy updatePolicy(int id, Policy policy) throws Exception;

	// Delete
	public void deletePolicy(int id);

}
