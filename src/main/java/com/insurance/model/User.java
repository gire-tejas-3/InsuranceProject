package com.insurance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String username;
	private String name;
	private String mobileNo;
	private String email;
	private String address;
	private String role;
	private boolean isActive;
	private Integer policyId; // (FK)-MTO

	// Constructors
	public User() {

	}

	public User(String username, String name, String mobileNo, String email, String address, String role,
			boolean isActive, Integer policyId) {
		super();
		this.username = username;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.address = address;
		this.role = role;
		this.isActive = isActive;
		this.policyId = policyId;
	}

	// Getter Setter Methods
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", name=" + name + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", address=" + address + ", role=" + role + ", isActive=" + isActive
				+ ", policyId=" + policyId + "]";
	}

}
