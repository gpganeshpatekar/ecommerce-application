package com.ecom.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.JwtRequest;
import com.ecom.payload.JwtResponse;
import com.ecom.payload.UserDto;
import com.ecom.security.JwtHelper;
import com.ecom.services.impl.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtHelper helper;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws Exception {

//		authenticate
		this.authenticateUser(request.getUsername(), request.getPassword());
//		if authenticate then.. we have to create authenticationManager bean in security configuration file..
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.helper.generateToken(userDetails);
		
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setToken(token);
		jwtResponse.setUser(this.modelMapper.map(userDetails, UserDto.class));
		
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}

	private void authenticateUser(String username, String password) throws Exception {

		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("invalid userename and password..");
		} catch (DisabledException e) {
			throw new Exception("user is not active");
		}

	}

}
