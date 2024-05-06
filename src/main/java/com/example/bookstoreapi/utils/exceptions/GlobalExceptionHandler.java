package com.example.bookstoreapi.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.bookstoreapi.utils.Resposta;

@ControllerAdvice
public class GlobalExceptionHandler {

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

	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<Resposta<RuntimeException>> handleRuntimeException(RuntimeException exception) {
		Resposta<RuntimeException> resposta = Resposta.setRetornoERRO(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
	
}
