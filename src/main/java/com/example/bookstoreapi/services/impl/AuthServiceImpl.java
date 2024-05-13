package com.example.bookstoreapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookstoreapi.models.vo.security.AccountCredentialsVO;
import com.example.bookstoreapi.models.vo.security.TokenVO;
import com.example.bookstoreapi.repositories.UserRepository;
import com.example.bookstoreapi.services.AuthService;
import com.example.bookstoreapi.utils.exceptions.InvalidJwtAuthenticationException;
import com.example.bookstoreapi.utils.security.jwt.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
	private JwtTokenProvider tokenProvider;

    @Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<?> signin(AccountCredentialsVO credentials) {
        if (checkIfCredentialsIsNull(credentials))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials data!");
        try {
            var username = credentials.getUsername();
            var password = credentials.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);
            TokenVO tokenResponse = null;

            if(user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username "+username+" not found!");
            }

            if(tokenResponse == null)
				throw new InvalidJwtAuthenticationException("Invalid client request!");

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }
    
	public ResponseEntity<?> refreshToken(String username, String refreshToken) {
        if (checkIfRefreshTokenIsNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refreshToken data!");

		var user = repository.findByUsername(username);
		TokenVO tokenResponse = null;

		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
        
        if (tokenResponse == null)
			throw new InvalidJwtAuthenticationException("Invalid client request!");
        
        return ResponseEntity.ok(tokenResponse);
	}

    private boolean checkIfRefreshTokenIsNull(String username, String refreshToken) {
        return     refreshToken == null || refreshToken.isBlank()
                || username == null || username.isBlank();
    }

    private boolean checkIfCredentialsIsNull(AccountCredentialsVO credentials) {
        return     credentials == null 
                || credentials.getUsername() == null || credentials.getUsername().isBlank()
                || credentials.getPassword() == null || credentials.getPassword().isBlank();
    }
}