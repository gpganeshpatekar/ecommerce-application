package com.ecom.payload;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	
	private Integer userId;
	private String name;
	// consider email as user name
	private String email;
	private String password;
	private String about;
	private String gender;
	private String address;
	private String phone;
	private boolean active;


}
