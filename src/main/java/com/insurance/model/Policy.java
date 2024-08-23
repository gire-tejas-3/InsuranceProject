package com.insurance.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

	@Pattern(regexp = "^(AVAILABLE|INPROCESS|APPROVED|REJECTED)$")
	private String status = "AVAILABLE";
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	
	// Constructor

	public Policy() {
		this.policyNumber = generatePolicyNumber();
	}

	public Policy(String type, String paymentMode, String payTerm, double settlementRatio, String kycDocuments,
			LocalDate startDate, LocalDate expireDate, long coverageAmount, String status) {
		this();
		this.type = type;
		this.paymentMode = paymentMode.toUpperCase();
		this.payTerm = payTerm.toUpperCase();
		this.settlementRatio = settlementRatio;
		this.kycDocuments = kycDocuments;
		this.startDate = startDate;
		this.expireDate = expireDate;
		this.coverageAmount = coverageAmount;
		this.status = status.toUpperCase();
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
		this.paymentMode = paymentMode.toUpperCase();
	}

	public String getPayTerm() {
		return payTerm;
	}

	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm.toUpperCase();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyNumber=" + policyNumber + ", type=" + type + ", paymentMode=" + paymentMode
				+ ", payTerm=" + payTerm + ", settlementRatio=" + settlementRatio + ", kycDocuments=" + kycDocuments
				+ ", startDate=" + startDate + ", expireDate=" + expireDate + ", coverageAmount=" + coverageAmount
				+ ", status=" + status + "]";
	}

	// Create Method for Generate auto policyNumber

	public String generatePolicyNumber() {
		Instant now = Instant.now();
		LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYHHmmss");
		String timestamp = dateTime.format(formatter);

		return "POL" + timestamp;
	}

}
