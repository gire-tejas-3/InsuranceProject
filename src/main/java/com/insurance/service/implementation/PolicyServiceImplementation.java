package com.insurance.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public Policy updatePolicy(int id, Policy policy) throws Exception {
		Policy exsistingPolicy = policyRepository.findById(id);
		if (exsistingPolicy == null) {
			throw new Exception("Policy is not found");
		}
		return policyRepository.save(policy);
	}

	@Override
	public void deletePolicy(int id) {
		policyRepository.deleteById(id);
	}
}
