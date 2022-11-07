package com.ecom.payload;


import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	
	private Integer userId;
	@NotEmpty
	@Size(min = 4, max = 20, message = "name must be min of 4 characters and max of 20 characters")
	private String name;
	// consider email as user name
	@Email
	@Pattern(regexp = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+",message = "valid email id is required")
	private String email;
	@NotEmpty
	@Size(min = 6,message = "password must be min of 6 characters")
	private String password;
	@NotEmpty
	private String about;
	private String gender;
	private String address;
	@NotEmpty
	@Pattern(regexp = "[6-9][0-9]{9}",message = "valid 10 digit mobile number required without country code eg. +91")
	private String phone;
	private boolean active;

	private Set<RoleDto> roles = new HashSet<>();

	@JsonIgnore // password won't get
	public String getPassword() {
		return password;
	}

	@JsonProperty // but you can set password
	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	
	

}
