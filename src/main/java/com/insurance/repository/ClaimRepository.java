package com.insurance.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurance.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Serializable> {

	public Claim findById(Integer id);

	public void deleteById(Integer id);
}
