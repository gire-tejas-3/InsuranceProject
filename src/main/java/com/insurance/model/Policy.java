package com.insurance.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Random;

@Entity
@Table(name = "policy")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String policyNumber;

	@NotNull
	private String type;

	@Pattern(regexp = "^(ONLINE|OFFLINE|AUTOPAYMENT)$")
	private String paymentMode; // online, offline, auto payment

	@Pattern(regexp = "^(YEARLY|QUARTERLY|MONTHLY)$")
	private String payTerm; // yearly, quaterly,semi-quaterly,monthly,daily

	@NotNull
	private double settlementRatio;
	private String kycDocuments; // adharcard or pancard or passport(mapped with entity)

	@NotNull
	private LocalDate startDate;

	@NotNull
	private LocalDate expireDate;

	@NotNull
	private long coverageAmount;

	@OneToMany(mappedBy = "policyId")
	private List<Nominee> nomineeList;

	// Constructor

	public List<Nominee> getNomineeList() {
		return nomineeList;
	}

	public void setNomineeList(List<Nominee> nomineeList) {
		this.nomineeList = nomineeList;
	}

	public Policy() {

	}

	public Policy(String type, String paymentMode, String payTerm, double settlementRatio, String kycDocuments,
			LocalDate startDate, LocalDate expireDate, long coverageAmount) {
		this.type = type;
		this.paymentMode = paymentMode;
		this.payTerm = payTerm;
		this.settlementRatio = settlementRatio;
		this.kycDocuments = kycDocuments;
		this.startDate = startDate;
		this.expireDate = expireDate;
		this.coverageAmount = coverageAmount;
	}

	// Getter Setter Method

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPayTerm() {
		return payTerm;
	}

	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm;
	}

	public double getSettlementRatio() {
		return settlementRatio;
	}

	public void setSettlementRatio(double settlementRatio) {
		this.settlementRatio = settlementRatio;
	}

	public String getKycDocuments() {
		return kycDocuments;
	}

	public void setKycDocuments(String kycDocuments) {
		this.kycDocuments = kycDocuments;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public long getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(long coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyNumber=" + policyNumber + ", type=" + type + ", paymentMode=" + paymentMode
				+ ", payTerm=" + payTerm + ", settlementRatio=" + settlementRatio + ", kycDocuments=" + kycDocuments
				+ ", startDate=" + startDate + ", expireDate=" + expireDate + ", coverageAmount=" + coverageAmount
				+ ", nomineeList=" + nomineeList + "]";
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
