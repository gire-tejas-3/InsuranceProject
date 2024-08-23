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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "claim")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String claimNumber;
	private String description;

	@NotNull
	private LocalDate claimDate;

	@Pattern(regexp = "^(AVAILABLE|INPROCESS|APPROVED|REJECTED)$")
	private String status = "AVAILABLE"; // Aprroved or Rejected or in process

	@NotNull
	private long claimAmount;

	@NotNull
	private int gracePeriod; // 30DAYS OR 60 DAYS

	@Pattern(regexp = "^(MATURITY|DEATH)$")
	private String category; // maturity or death

	@Pattern(regexp = "^(CHEQUE|TRANSFER)$")
	private String modeOfPayment; // cheque, digital transfer

	private String medicalRecords; // medical bills, hospital bills

	// private int policyId;

	// Constructor

	public Claim() {
		this.claimNumber = generateClaimNumber();
	}

	public Claim(String description, LocalDate claimDate, String status, long claimAmount, int gracePeriod,
			String category, String modeOfPayment, String medicalRecords) {
		this();
		this.description = description;
		this.claimDate = claimDate;
		this.claimAmount = claimAmount;
		this.gracePeriod = gracePeriod;
		this.category = category.toUpperCase();
		this.modeOfPayment = modeOfPayment.toUpperCase();
		this.medicalRecords = medicalRecords;
		this.status = status.toUpperCase();
	}

	// Getter Setter Method

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}

	public long getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(long claimAmount) {
		this.claimAmount = claimAmount;
	}

	public int getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(int gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category.toUpperCase();
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment.toUpperCase();
	}

	public String getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(String medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	// To String Method

	@Override
	public String toString() {
		return "Claim [id=" + id + ", claimNumber=" + claimNumber + ", description=" + description + ", claimDate="
				+ claimDate + ", status=" + status + ", claimAmount=" + claimAmount + ", gracePeriod=" + gracePeriod
				+ ", category=" + category + ", modeOfPayment=" + modeOfPayment + ", medicalRecords=" + medicalRecords
				+ "]";
	}

	// Create method for generate auto claim number

	public String generateClaimNumber() {
		Instant now = Instant.now();
		LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYHHmmss");
		String timestamp = dateTime.format(formatter);

		return "CL" + timestamp;
	}

}
