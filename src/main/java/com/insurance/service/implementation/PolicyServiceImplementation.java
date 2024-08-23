package com.insurance.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exceptions.PolicyNotFoundException;
import com.insurance.model.Policy;
import com.insurance.repository.PolicyRepository;
import com.insurance.service.PolicyService;

@Service
public class PolicyServiceImplementation implements PolicyService {

	@Autowired
	private PolicyRepository policyRepository;

	@Override
	public Policy createPolicy(Policy policy) {
		return policyRepository.save(policy);
	}

	@Override
	public List<Policy> getAllPolicy() {
		return policyRepository.findAll();
	}

	@Override
	public Policy findById(int id) {
		return policyRepository.findById(id);
	}

	@Override
	public Policy updatePolicy(int id, Policy policy) {
		Policy exsistingPolicy = policyRepository.findById(id);
		if (exsistingPolicy == null) {
			throw new PolicyNotFoundException("Policy is not found");
		}
		return policyRepository.save(policy);
	}

	@Override
	public void deletePolicy(int id) {
		policyRepository.deleteById(id);
	}

	@Override
	public Policy updatePolicyStatus(int id, String status) throws Exception {
		Policy exsistingPolicy = policyRepository.findById(id);
		if (exsistingPolicy == null) {
			throw new PolicyNotFoundException("Policy is not found");
		}

		if (!exsistingPolicy.getStatus().equals("INPROCESS")) {
			throw new Exception("Only policies with `INPROCESS` status can be updated");
		}

		exsistingPolicy.setStatus(status);
		return policyRepository.save(exsistingPolicy);
	}
}
