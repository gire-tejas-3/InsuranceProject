package com.insurance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "nominee")
public class Nominee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 3, message = "Please enter valid name, name must not be less then 3.")
	private String name;

	@Pattern(regexp = "^(female|male)$", message = "Gender must be either male or female.")
	private String gender;

	@NotNull(message = "Please enter relationship with the user.")
	private String relationship;

	@Pattern(regexp = "^[0-9]{10,13}$", message = "Please enter valid mobile number.")
	private String mobileNo;

	@Email(message = "Please enter the valid email address.")
	private String email;

	@NotNull(message = "Date of birth is mandatory.")
	private String dateOfBirth;

	@NotNull(message = "Please enter your address.")
	@OneToOne(mappedBy = "nominee")
	@JsonManagedReference
	private List<Address> address;

	@JoinColumn(name = "policyId", insertable = false, updatable = false)
	@ManyToOne
	private Integer policyId; // (FK) MTO

	public Nominee() {

	}

	public Nominee(String name, String gender, String relationship, String mobileNo, String email, String dateOfBirth,
			List<Address> address, Integer policyId) {

		this.name = name;
		this.gender = gender;
		this.relationship = relationship;
		this.mobileNo = mobileNo;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.policyId = policyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	@Override
	public String toString() {
		return "Nominee [id=" + id + ", name=" + name + ", gender=" + gender + ", relationship=" + relationship
				+ ", mobileNo=" + mobileNo + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", address="
				+ address + ", policyId=" + policyId + "]";
	}

}
