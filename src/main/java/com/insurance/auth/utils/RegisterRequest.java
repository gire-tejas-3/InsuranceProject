package com.insurance.auth.utils;

import java.time.LocalDate;

import com.insurance.model.MaritalStatus;
import com.insurance.model.UserRole;

public class RegisterRequest {
	private String username;
	private String name;
	private String mobileNo;
	private String email;
	private String password;
	private LocalDate birthDate;
	private String gender;
	private MaritalStatus maritalStatus;
	private UserRole role;

	public RegisterRequest() {
	}

	public RegisterRequest(String username, String name, String mobileNo, String email, String password,
			LocalDate birthDate, String gender, MaritalStatus maritalStatus, UserRole role) {
		super();
		this.username = username;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email.toLowerCase();
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
		this.maritalStatus = MaritalStatus.UNMARRIED;
		this.role = UserRole.USER;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

}
