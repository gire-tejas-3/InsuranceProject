package com.insurance.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name = "policy")
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private final String policyNumber;

	private String type;

	@Enumerated(EnumType.STRING)
	@Pattern(regexp = "^(ONLINE|OFFLINE|AUTOPAYMENT)$")
	private PaymentMode paymentMode; // online, offline, auto payment

	@Enumerated(EnumType.STRING)
	@Pattern(regexp = "^(YEARLY|QUARTERLY|MONTHLY)$")
	private PayTerm payTerm;

	private double settlementRatio;
	private String kycDocuments;

	private LocalDate startDate;

	private LocalDate expireDate;

	private long coverageAmount;

	@Enumerated(EnumType.STRING)
	@Pattern(regexp = "^(AVAILABLE|INPROCESS|APPROVED|REJECTED)$")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "policyId")
	private List<Nominee> nomineeList;

	private static String generatePolicyNumber() {
		Instant now = Instant.now();
		LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYHHmmss");
		String timestamp = dateTime.format(formatter);

		return "RMT" + timestamp;
	}

	public Policy() {
		this.policyNumber = generatePolicyNumber();
	}

	public Policy(String type, PaymentMode paymentMode, PayTerm payTerm, double settlementRatio, String kycDocuments,
			LocalDate startDate, LocalDate expireDate, long coverageAmount, Status status, User user,
			List<Nominee> nomineeList) {
		this();
		this.type = type;
		this.paymentMode = paymentMode;
		this.payTerm = payTerm;
		this.settlementRatio = settlementRatio;
		this.kycDocuments = kycDocuments;
		this.startDate = startDate;
		this.expireDate = expireDate;
		this.coverageAmount = coverageAmount;
		this.status = status;
		this.user = user;
		this.nomineeList = nomineeList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public PayTerm getPayTerm() {
		return payTerm;
	}

	public void setPayTerm(PayTerm payTerm) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Nominee> getNomineeList() {
		return nomineeList;
	}

	public void setNomineeList(List<Nominee> nomineeList) {
		this.nomineeList = nomineeList;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	@Override
	public String toString() {
		return "Policy [id=" + id + ", policyNumber=" + policyNumber + ", type=" + type + ", paymentMode=" + paymentMode
				+ ", payTerm=" + payTerm + ", settlementRatio=" + settlementRatio + ", kycDocuments=" + kycDocuments
				+ ", startDate=" + startDate + ", expireDate=" + expireDate + ", coverageAmount=" + coverageAmount
				+ ", status=" + status + ", user=" + user + ", nomineeList=" + nomineeList + "]";
	}

}
