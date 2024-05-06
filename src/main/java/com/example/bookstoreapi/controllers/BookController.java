package com.example.bookstoreapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.services.BookService;
import com.example.bookstoreapi.utils.Resposta;
import com.example.bookstoreapi.vo.BookVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/v1/books")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(value = { "" })
	public ResponseEntity<Resposta<Page<BookVO>>> getAllBooks(
				@RequestParam(value = "title", required = false)	String title,
				@RequestParam(value = "page",  required = false, defaultValue = "0")	Integer page,
				@RequestParam(value = "size",  required = false, defaultValue = "10")	Integer size,
				@RequestParam(value = "order", required = false, defaultValue = "asc")	String order
	) {

		Direction sortDirection = "desc".equalsIgnoreCase(order) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));

		Resposta<Page<BookVO>> resposta;
		if(title != null)
			resposta = Resposta.setRetornoOK(bookService.findByTitleContaining(title, pageable));
		else
			resposta = Resposta.setRetornoOK(bookService.findAll(pageable));
		
		return ResponseEntity.ok(resposta);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<Resposta<BookVO>> getBookById(@PathVariable(required = true) Long id) {
		Resposta<BookVO> resposta = Resposta.setRetornoOK(bookService.findById(id));
		return ResponseEntity.ok(resposta);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<Resposta<BookVO>> createBook(@RequestBody Book book) {
		Resposta<BookVO> resposta = Resposta.setRetornoOK(bookService.create(book));
		return ResponseEntity.ok(resposta);
	}

	@PutMapping(value = { "" })
	public ResponseEntity<Resposta<BookVO>> updateBook(@RequestBody Book book) {
		Resposta<BookVO> resposta = Resposta.setRetornoOK(bookService.update(book));
		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Resposta<?>> deleteBookById(@PathVariable(required = true) Long id) {
		bookService.deleteById(id);

		Resposta<?> resposta = Resposta.setRetornoOK();
		return ResponseEntity.ok(resposta);
	}

	@PostMapping("/create-many")
	public ResponseEntity<Resposta<?>> createBooks(@RequestBody List<Book> bookList) {
		int count = 0;

		for (Book book : bookList) {
			BookVO bookVOSaved = bookService.create(book);
			if (bookVOSaved != null) {
				count++;
			}
		}

		Resposta<?> resposta = Resposta.setRetornoOK("Total of " + count + " Books were created");
		return ResponseEntity.ok(resposta);
	}

}
