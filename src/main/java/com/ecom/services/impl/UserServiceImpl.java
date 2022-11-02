package com.ecom.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.User;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.payload.UserDto;
import com.ecom.repositories.UserRepository;
import com.ecom.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto create(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		User save = this.userRepository.save(user);
		return this.modelMapper.map(save, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getByUserId(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with user id : " + userId));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getByEmail(String email) {
		User user = this.userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("user not found with email : "+email));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto update(Integer userId, UserDto userDto) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with user id : " + userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setGender(userDto.getGender());
		user.setAddress(userDto.getAddress());
		user.setPhone(userDto.getPhone());
		user.setActive(userDto.isActive());
		User updatedUser = this.userRepository.save(user);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void delete(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with user id : " + userId));
		this.userRepository.delete(user);
	}

}
