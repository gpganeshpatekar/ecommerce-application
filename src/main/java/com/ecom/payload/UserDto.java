package com.ecom.payload;


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
