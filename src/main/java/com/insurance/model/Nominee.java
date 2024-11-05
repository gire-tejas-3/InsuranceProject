package com.insurance.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	private String name;

	private String gender;

	private String relationship;

	private String mobileNo;

	private String email;

	private String dateOfBirth;

	@OneToMany(mappedBy = "nominee")
	@JsonManagedReference
	private List<Address> address;

	@ManyToOne
	@JoinColumn(name = "policy_id", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonBackReference
	private Policy policy;

	public Nominee() {

	}

	public Nominee(String name, String gender, String relationship, String mobileNo, String email, String dateOfBirth,
			List<Address> address, Policy policy) {
		super();
		this.name = name;
		this.gender = gender;
		this.relationship = relationship;
		this.mobileNo = mobileNo;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.policy = policy;
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

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	@Override
	public String toString() {
		return "Nominee [id=" + id + ", name=" + name + ", gender=" + gender + ", relationship=" + relationship
				+ ", mobileNo=" + mobileNo + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", address="
				+ address + ", policy=" + policy + "]";
	}

}
