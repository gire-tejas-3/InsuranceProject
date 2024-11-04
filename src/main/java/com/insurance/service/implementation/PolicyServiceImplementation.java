package com.insurance.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.exceptions.PolicyNotFoundException;
import com.insurance.model.Policy;
import com.insurance.model.Status;
import com.insurance.model.User;
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
	public List<Policy> getAllPolicy(String status) {
		List<Policy> policies;
		if (status != null) {
			policies = policyRepository.findAll().stream().filter(policy -> policy.getStatus().equals(status)).toList();
		}
		policies = policyRepository.findAll();
		return policies;
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

		exsistingPolicy.setType("AVAILABLE");
		exsistingPolicy.setPaymentMode(policy.getPaymentMode());
		exsistingPolicy.setPayTerm(policy.getPayTerm());
		exsistingPolicy.setSettlementRatio(policy.getSettlementRatio());

		return policyRepository.save(exsistingPolicy);
	}

	@Override
	public void deletePolicy(int id) {
		Policy exsistingPolicy = policyRepository.findById(id);
		if (exsistingPolicy == null) {
			throw new PolicyNotFoundException("Policy is not found");
		}

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

		exsistingPolicy.setStatus(Status.valueOf(status));
		return policyRepository.save(exsistingPolicy);
	}
}
