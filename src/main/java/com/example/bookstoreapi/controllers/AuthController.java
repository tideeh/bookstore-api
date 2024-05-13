package com.example.bookstoreapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreapi.models.vo.security.AccountCredentialsVO;
import com.example.bookstoreapi.services.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@Tag(name = "Authentication Endpoint")
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService service;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO credentials) {
		return service.signin(credentials);
	}

	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity<?> refreshToken(
			@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {

		return service.refreshToken(username, refreshToken);
	}

}