package com.reviews.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "reviewUser")
public class User {
	
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column(name = "username", unique=true)
	private String username;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "resetPending")
	private boolean resetPending;
	
	@Transient
	@JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	public User() {
		
	}
	
	public User(int userId) {
		this.userId = userId;
	}

	public User(int userId, String username, String firstname, String lastname, String email, boolean resetPending, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.resetPending = resetPending;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isResetPending() {
		return resetPending;
	}

	public void setResetPending(boolean resetPending) {
		this.resetPending = resetPending;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + "]";
	}

}
