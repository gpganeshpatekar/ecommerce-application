package com.ecom.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// To get the token
		// Authorization = Bearer 2354235423.dkfjdksf.sdfkljdsfj
		String requestToken = request.getHeader("Authorization");
		logger.info("message {} ", requestToken);
		String username = null;
		String jwtToken = null;

		if (requestToken != null && requestToken.trim().startsWith("Bearer")) {

			jwtToken = requestToken.substring(7);
			// get the username from token
			try {
				username = this.jwtHelper.getUsernameFromToken(jwtToken);
			} catch (ExpiredJwtException e) {
				logger.info("invalid token message {} ", "Jwt Token Expired");
			} catch (MalformedJwtException e) {
				logger.info("invalid token message {} ", "Invalid Jwt Token");
			} catch (IllegalArgumentException e) {
				logger.info("invalid token message {} ", "unable to get token");
			}
		} else {
			logger.info("token message {} ", "token does not starts with bearer");
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			// validate
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if(this.jwtHelper.validateToken(jwtToken, userDetails)) {
				
				// authentication set 
				
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}else {
				
				logger.info("Not validated message {} ","invalid jwt token");
			}
		}else {
			
			logger.info("username message {} ","username is null or auth is already there");
			
		}

		filterChain.doFilter(request, response);

	}

}
