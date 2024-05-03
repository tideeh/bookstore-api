package com.example.bookstoreapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.services.BookService;
import com.example.bookstoreapi.utils.JsonUtil;
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

	@GetMapping(value = { "", "/" })
	public ResponseEntity<Resposta> getAllBooks(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "order", required = false) String order) {
		List<Book> bookList = bookService.findAll(title, order);
		List<BookVO> bookVOList = new ArrayList<>();
		for (Book book : bookList) {
			bookVOList.add(JsonUtil.jsonToObject(book.toString(), BookVO.class));
		}

		Resposta resposta = Resposta.setRetornoOK(bookVOList);
		return ResponseEntity.ok(resposta);
	}

	@GetMapping(value = { "/{id}" })
	public ResponseEntity<Resposta> getBookById(@PathVariable(required = true) Long id) {
		Book book = bookService.findById(id);
		BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);

		Resposta resposta = Resposta.setRetornoOK(bookVO);
		return ResponseEntity.ok(resposta);
	}

	@PostMapping(value = { "", "/" })
	public ResponseEntity<Resposta> createBook(@RequestBody Book book) {
		Book bookSaved = bookService.save(book);
		BookVO bookVO = JsonUtil.jsonToObject(bookSaved.toString(), BookVO.class);
		Resposta resposta = Resposta.setRetornoOK(bookVO);
		return ResponseEntity.ok(resposta);
	}

	@PutMapping(value = { "/{id}" })
	public ResponseEntity<Resposta> updateBookById(@PathVariable(required = true) Long id, @RequestBody Book newBook) {
		Book book = bookService.findById(id);

		book.setTitle(newBook.getTitle());
		book.setAuthor(newBook.getAuthor());
		book.setCategory(newBook.getCategory());
		book.setLanguage(newBook.getLanguage());
		book.setPrice(newBook.getPrice());
		book = bookService.save(book);

		BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);
		Resposta resposta = Resposta.setRetornoOK(bookVO);
		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping(value = { "", "/" })
	public ResponseEntity<Resposta> deleteBook(@RequestBody Book book) {
		bookService.delete(book);

		Resposta resposta = Resposta.setRetornoOK();
		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping(value = { "/{id}" })
	public ResponseEntity<Resposta> deleteBookById(@PathVariable(required = true) Long id) {
		bookService.deleteById(id);

		Resposta resposta = Resposta.setRetornoOK();
		return ResponseEntity.ok(resposta);
	}

	@PostMapping("/create-many")
	public ResponseEntity<Resposta> createBooks(@RequestBody List<Book> bookList) {
		int count = 0;

		for (Book book : bookList) {
			Book bookSaved = bookService.save(book);
			if (bookSaved != null) {
				count++;
			}
		}

		Resposta resposta = Resposta.setRetornoOK("Total of " + count + " Books were created");
		return ResponseEntity.ok(resposta);
	}

}
