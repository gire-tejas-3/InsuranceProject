package com.insurance.auth.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.insurance.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "refresh_token")
public class Refreshtoken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String token;
	private Date issuedAt;
	private Date expiration;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonBackReference
	private User user;

	public Refreshtoken() {
		// TODO Auto-generated constructor stub
	}

	public Refreshtoken(String token, Date issuedAt, Date expiration, User user) {
		super();
		this.token = token;
		this.issuedAt = issuedAt;
		this.expiration = expiration;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RefreshToken [id=" + id + ", token=" + token + ", issuedAt=" + issuedAt + ", expiration=" + expiration
				+ ", user=" + user + "]";
	}

}
