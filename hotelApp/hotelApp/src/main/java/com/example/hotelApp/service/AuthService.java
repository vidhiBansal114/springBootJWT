package com.example.hotelApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.hotelApp.dto.JwtRequest;
import com.example.hotelApp.dto.JwtResponse;
import com.example.hotelApp.jwt.JwtAuthenticationHelper;
@Service
public class AuthService {
	@Autowired
	AuthenticationManager manager;
	@Autowired 
	JwtAuthenticationHelper jwtHelper;
	@Autowired
	UserDetailsService userDetailsService;

	public JwtResponse login(JwtRequest jwtRequest) {
		this.doAuthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		UserDetails userDetails= userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		String token = jwtHelper.generateToken(userDetails);
		JwtResponse response=JwtResponse.builder().JwtToken(token).build();
		return response;
		// TODO Auto-generated method stub
		
	}

	private void doAuthenticate(String username, String password) {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try{
			manager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username / password");
			
		}
		
	}

}
