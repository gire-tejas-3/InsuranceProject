package com.insurance.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.model.Nominee;
import com.insurance.repository.NomineeRepository;
import com.insurance.service.NomineeService;

@Service
public class NomineeServiceImpl implements NomineeService {

	@Autowired
	private NomineeRepository nomineeRepository;

	@Override
	public Nominee saveNominee(Nominee nominee) {
		Nominee nominee1 = nomineeRepository.save(nominee);
		return nominee1;
	}

	@Override
	public Nominee updateNominee(Nominee nominee) {
		Nominee nominee1 = nomineeRepository.save(nominee);
		return nominee1;
	}

	@Override
	public Nominee getNomineeById(int id) {
		Nominee nominee = nomineeRepository.findById(id);
		return nominee;
	}

	@Override
	public void deleteNominee(int id) {
		nomineeRepository.deleteById(id);
	}

}
