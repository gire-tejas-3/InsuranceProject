package com.insurance.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.insurance.auth.model.Refreshtoken;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	private static final long serialVersionUID = 7626569018765875940L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String username;

	private String name;

	@Size(min = 10, max = 13, message = "Please enter a valid mobile number.")
	private String mobileNo;

	@Column(unique = true)
	private String email;

	private String password;

	private LocalDate birthDate;

	@Pattern(regexp = "^(female|male)$")
	private String gender;

	private String occupation;
	private String nationality;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> address;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	private boolean isActive;

	@OneToOne(mappedBy = "user")
	@JsonManagedReference
	private Refreshtoken refreshToken;

//	Constructors
	public User() {

	}

	public User(String username, String name, String mobileNo, String email, String password, LocalDate birthDate,
			String gender, String occupation, String nationality, List<Address> address, UserRole role,
			MaritalStatus maritalStatus, boolean isActive, Refreshtoken refreshToken) {
		this.username = username;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
		this.occupation = occupation;
		this.nationality = nationality;
		this.address = address;
		this.role = role;
		this.maritalStatus = maritalStatus;
		this.isActive = isActive;
		this.refreshToken = refreshToken;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Refreshtoken getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(Refreshtoken refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", mobileNo=" + mobileNo + ", email="
				+ email + ", password=" + password + ", birthDate=" + birthDate + ", gender=" + gender + ", occupation="
				+ occupation + ", nationality=" + nationality + ", address=" + address + ", role=" + role
				+ ", maritalStatus=" + maritalStatus + ", isActive=" + isActive + "]";
	}

}
