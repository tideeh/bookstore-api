package com.example.bookstoreapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.models.vo.BookVO;
import com.example.bookstoreapi.services.BookService;
import com.example.bookstoreapi.utils.Resposta;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/v1/books")
@CrossOrigin(origins = "*")
public class BookController {

	@Autowired
	private BookService service;

	@GetMapping(value = { "" })
	public ResponseEntity<Resposta<Page<BookVO>>> getAll(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(value = "order", required = false, defaultValue = "asc") String order) {

		Order sortOrder = "desc".equalsIgnoreCase(order) ? Sort.Order.desc("title") : Sort.Order.asc("title");
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortOrder.ignoreCase()));

		Resposta<Page<BookVO>> resposta;
		if (title != null && !title.isEmpty())
			resposta = Resposta.setRetornoOK(service.findByTitleContaining(title, pageable));
		else
			resposta = Resposta.setRetornoOK(service.findAll(pageable));

		return ResponseEntity.ok(resposta);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<Resposta<BookVO>> getById(@PathVariable(required = true) Long id) {
		Resposta<BookVO> resposta = Resposta.setRetornoOK(service.findById(id));
		return ResponseEntity.ok(resposta);
	}

	@PostMapping(value = { "" })
	public ResponseEntity<Resposta<BookVO>> create(@RequestBody Book entity) {
		Resposta<BookVO> resposta = Resposta.setRetornoOK(service.create(entity));
		return ResponseEntity.ok(resposta);
	}

	@PutMapping(value = { "" })
	public ResponseEntity<Resposta<BookVO>> update(@RequestBody Book entity) {
		Resposta<BookVO> resposta = Resposta.setRetornoOK(service.update(entity));
		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Resposta<?>> deleteById(@PathVariable(required = true) Long id) {
		service.deleteById(id);
		Resposta<?> resposta = Resposta.setRetornoOK();
		return ResponseEntity.ok(resposta);
	}

	@PostMapping("/create-many")
	public ResponseEntity<Resposta<?>> createMany(@RequestBody List<Book> entityList) {
		int count = 0;
		for (Book entity : entityList) {
			BookVO entityVOSaved = service.create(entity);
			if (entityVOSaved != null) {
				count++;
			}
		}

		Resposta<?> resposta = Resposta.setRetornoOK("Total of " + count + " "+Book.class.getSimpleName()+"s were created!");
		return ResponseEntity.ok(resposta);
	}

}
