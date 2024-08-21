package com.insurance.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 4, max = 12, message = "Please enter a valid username. Username should not be less than 4 and should not be greater than 12 characters.")
	private String username;

	@Size(min = 4, message = "Please enter a valid name. Name should not be less than 4 characters.")
	private String name;

	@Size(min = 10, max = 13, message = "Please enter a valid mobile number.")
	@Pattern(regexp = "^[0-9]{10, 13}$", message = "Mobile Number should contain only digits")
	private String mobileNo;

	@Email(message = "Please enter a valid email")
	private String email;

	@Pattern(regexp = "^(ADMIN|USER)$")
	private Set<String> role;

	private boolean isActive;

	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,16}$", message = "Password must be 8 to 16 characters long, contain at least one digit, one uppercase letter, and one special symbol.")
	private String password;

	private LocalDate birthDate;

	@Pattern(regexp = "^(female|male)$")
	private String gender;

	private boolean maritalStatus;
	private String occupation;
	private String nationality;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> address;

//	Constructors
	public User() {

	}

	public User(String username, String name, String mobileNo, String email, Set<String> role, boolean isActive,
			String password, LocalDate birthDate, String gender, boolean maritalStatus, List<Address> address,
			String occupation, String nationality) {
		this.username = username;
		this.name = name;
		this.mobileNo = mobileNo;
		this.email = email;
		this.role = role;
		this.isActive = isActive;
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.address = address;
		this.occupation = occupation;
		this.nationality = nationality;
	}

// Getter Setter Methods
	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
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

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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

	public boolean isMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", mobileNo=" + mobileNo + ", email="
				+ email + ", role=" + role + ", isActive=" + isActive + ", password=" + password + ", birthDate="
				+ birthDate + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", address=" + address
				+ ", occupation=" + occupation + ", nationality=" + nationality + "]";
	}

}
