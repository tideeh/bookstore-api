package com.example.helloworld.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.helloworld.utils.Resposta;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({SearchNotFoundException.class})
	public ResponseEntity<Resposta> SearchNotFoundException(SearchNotFoundException exception) {
		Resposta resposta = new Resposta().setRetornoERRO(exception.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
	}

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<Resposta> handleRuntimeException(RuntimeException exception) {
		Resposta resposta = new Resposta().setRetornoERRO(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
	
}
