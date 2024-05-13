package com.example.bookstoreapi.services;

import org.springframework.http.ResponseEntity;

import com.example.bookstoreapi.models.vo.security.AccountCredentialsVO;

public interface AuthService {
	ResponseEntity<?> signin(AccountCredentialsVO credentials);
	ResponseEntity<?> refreshToken(String username, String refreshToken);
}
