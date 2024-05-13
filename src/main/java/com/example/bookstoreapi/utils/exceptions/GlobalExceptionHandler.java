package com.example.bookstoreapi.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.bookstoreapi.utils.Resposta;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<Resposta<RuntimeException>> handleRuntimeException(RuntimeException exception) {
		Resposta<RuntimeException> resposta = Resposta.setRetornoERRO(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }

	@ExceptionHandler({SearchNotFoundException.class})
	public ResponseEntity<Resposta<SearchNotFoundException>> SearchNotFoundException(SearchNotFoundException exception) {
		Resposta<SearchNotFoundException> resposta = Resposta.setRetornoERRO(exception);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
	}

	@ExceptionHandler({RequiredObjectIsNullException.class})
	public ResponseEntity<Resposta<RequiredObjectIsNullException>> RequiredObjectIsNullException(RequiredObjectIsNullException exception) {
		Resposta<RequiredObjectIsNullException> resposta = Resposta.setRetornoERRO(exception);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
	}

	@ExceptionHandler({InvalidJwtAuthenticationException.class})
	public ResponseEntity<Resposta<InvalidJwtAuthenticationException>> InvalidJwtAuthenticationException(InvalidJwtAuthenticationException exception) {
		Resposta<InvalidJwtAuthenticationException> resposta = Resposta.setRetornoERRO(exception);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
	}

	@ExceptionHandler({UsernameNotFoundException.class})
	public ResponseEntity<Resposta<UsernameNotFoundException>> UsernameNotFoundException(UsernameNotFoundException exception) {
		Resposta<UsernameNotFoundException> resposta = Resposta.setRetornoERRO(exception);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
	}

	@ExceptionHandler({BadCredentialsException.class})
	public ResponseEntity<Resposta<BadCredentialsException>> BadCredentialsException(BadCredentialsException exception) {
		Resposta<BadCredentialsException> resposta = Resposta.setRetornoERRO(exception);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
	}
	
}
