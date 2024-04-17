package com.example.hotelApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotelApp.dto.JwtRequest;
import com.example.hotelApp.dto.JwtResponse;
import com.example.hotelApp.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	AuthService authService;
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
		return new ResponseEntity<>(authService.login(jwtRequest),HttpStatus.OK);
	}
}
