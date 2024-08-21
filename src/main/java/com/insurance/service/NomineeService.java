package com.insurance.service;

import com.insurance.model.Nominee;

public interface NomineeService {
 
	public Nominee saveNominee(Nominee nominee);
	
	public Nominee updateNominee(Nominee nominee);
	
    public Nominee getNomineeById(int id);
    
    public void deleteNominee(int id);
}
