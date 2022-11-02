package com.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.UserDto;
import com.ecom.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	to create new user
	@PostMapping(value = "/",consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
		userDto.setActive(true);
		UserDto save = this.userService.create(userDto);
		return new ResponseEntity<UserDto>(save,HttpStatus.CREATED);
	}
//	to get all users
	@GetMapping(value = "/",produces = "application/json")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = this.userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
//  to get single user by id
	@GetMapping(value = "/{userId}",produces = "application/json")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId){
		UserDto user = this.userService.getByUserId(userId);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
//	to get single user by email
	@GetMapping(value = "email/{email}",produces = "application/json")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
		UserDto user = this.userService.getByEmail(email);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
//	to update user
	@PutMapping (value = "/{userId}",consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Integer userId,@RequestBody UserDto userDto){
		UserDto update = this.userService.update(userId, userDto);
		return new ResponseEntity<UserDto>(update,HttpStatus.OK);
	}
//	to delete user by id
	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.delete(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully ",true),HttpStatus.OK);
	}
}
