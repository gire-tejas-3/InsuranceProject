package com.insurance.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Random;

@Entity
@Table(name = "policy")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int policyId;
	private String policyNumber;
	private String policyType;
	private long policyPremium;
	private LocalDate policyStartDate;
	private LocalDate policyEndDate;
	private long policyCoverageAmount;

	// Constructors

	public Policy() {
		this.policyNumber = generatePolicyNumber();
	}

	public Policy(String policyType, long policyPremium, LocalDate policyStartDate, LocalDate policyEndDate,
			long policyCoverageAmount) {
		this.policyType = policyType;
		this.policyPremium = policyPremium;
		this.policyStartDate = policyStartDate;
		this.policyEndDate = policyEndDate;
		this.policyCoverageAmount = policyCoverageAmount;
	}

	// Getter Setter Methods

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public long getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(long policyPremium) {
		this.policyPremium = policyPremium;
	}

	public LocalDate getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(LocalDate policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public LocalDate getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(LocalDate policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public long getPolicyCoverageAmount() {
		return policyCoverageAmount;
	}

	public void setPolicyCoverageAmount(long policyCoverageAmount) {
		this.policyCoverageAmount = policyCoverageAmount;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = generatePolicyNumber();
	}

	// To String Method

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", policyType=" + policyType + ", policyPremium=" + policyPremium
				+ ", policyStartDate=" + policyStartDate + ", policyEndDate=" + policyEndDate
				+ ", policyCoverageAmount=" + policyCoverageAmount + "]";
	}

	// Create Method for Generate auto policyNumber

	public String generatePolicyNumber() {

		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		int length = 8;
		StringBuilder stringbuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			stringbuilder.append(characters.charAt(index));
		}
		return stringbuilder.toString();
	}

}
