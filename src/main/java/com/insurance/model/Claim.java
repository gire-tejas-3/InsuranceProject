package com.insurance.model;

import java.time.LocalDate;
import java.util.Random;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "claim")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int claimId;
	private String claimNumber;
	private String claimDescription;
	private LocalDate claimDate;
	private String claimStatus;
	private long claimAmount;
	// private int policyId;

	// Constructor

	public Claim() {
		this.claimNumber = generateClaimNumber();
	}

	public Claim(String claimDescription, LocalDate claimDate, String claimStatus, long claimAmount) {
		this.claimDescription = claimDescription;
		this.claimDate = claimDate;
		this.claimStatus = claimStatus;
		this.claimAmount = claimAmount;
	}

	// Getter Setter Method

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = generateClaimNumber();
	}

	public String getClaimDescription() {
		return claimDescription;
	}

	public void setClaimDescription(String claimDescription) {
		this.claimDescription = claimDescription;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public long getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(long claimAmount) {
		this.claimAmount = claimAmount;
	}

	// Create method for generate auto claim number

	public String generateClaimNumber() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		int length = 6;
		StringBuilder stringbuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			stringbuilder.append(characters.charAt(index));
		}
		return stringbuilder.toString();
	}

}
