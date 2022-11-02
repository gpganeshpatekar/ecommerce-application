package com.ecom.services;

import java.util.List;

import com.ecom.payload.UserDto;

public interface UserService {
	
//  create
	UserDto create(UserDto userDto);
//  get all
	List<UserDto> getAllUsers();
//  get by userId
	UserDto getByUserId(Integer userId);
//  get by email
	UserDto getByEmail(String email);
//  update
	UserDto update(Integer userId, UserDto userDto);
//  delete
	void delete(Integer userId);
	
	
	
	
	

}
