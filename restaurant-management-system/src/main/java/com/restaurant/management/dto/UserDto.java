package com.restaurant.management.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.restaurant.management.constraint.FieldMatch;

@FieldMatch.List({@FieldMatch(first="password", second="matchingPassword", message="Passwords must match!")})
public class UserDto {

	@NotBlank(message = "Username is required!")
	@Size(min = 6, max = 25, message="username must be at least 6 letters and at most 25!")
	private String username;
	
	@NotBlank(message = "First Name is required!")
	private String firstName;

	@NotBlank(message = "Last Name is required!")
	private String lastName; 

	@NotBlank(message = "Password is required!")
	@Size(min = 6, message="Password must be at least 6 characters!")
	private String password;
	
	@NotBlank
	private String matchingPassword;
	
	@Email(message = "Is not a valid Email!")
	@NotBlank(message = "Email is required!")
	private String email;
	
	private String access;
	
	public UserDto() {}
	
	public UserDto(@NotBlank(message="Username is required!") @Size(min = 6, max = 25, message="username must be at least 6 letters and at most 25!") String username, 
		       @NotBlank(message="Password is required!") @Size(min=6, message="password must be at least 6 letters") String password, 
		       @NotBlank String matchingPassword, 
		       @NotBlank(message="First Name is required!") String firstName, 
		       @NotBlank(message="Last Name is required!") String lastName, 
		       @NotBlank(message="Email is required!") String email,
		       String access) {
	
	this.username = username;
	this.password = password;
	this.matchingPassword = matchingPassword;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.access = access;
}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
}
