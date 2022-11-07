package com.ecom.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.entities.User;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load the user from database by username
		System.out.println("loading the user from database by username..");
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with username"));
		return user;
	}

}
