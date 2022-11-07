package com.ecom.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entities.Role;
import com.ecom.entities.User;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.RoleDto;
import com.ecom.payload.UserDto;
import com.ecom.repositories.RoleRepository;
import com.ecom.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	to create new user
	@PostMapping(value = "/",consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
		
		
		userDto.setActive(true);
		// to get normal user
		Role role = this.roleRepository.findById(5002).get();
//		Role role2 = new Role();
//		role2.setId(5002);
//		role2.setName("ROLE_NORMAL");
//		
//		Set<RoleDto> roles = new HashSet<>();
//		roles.add(this.modelMapper.map(role2, RoleDto.class));
//		userDto.setRoles(roles);
		userDto.getRoles().add(this.modelMapper.map(role, RoleDto.class));
		userDto.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
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
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") Integer userId,@RequestBody UserDto userDto){
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
