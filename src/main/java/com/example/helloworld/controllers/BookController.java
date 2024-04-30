package com.example.helloworld.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.models.Book;
import com.example.helloworld.services.BookService;
import com.example.helloworld.utils.JsonUtil;
import com.example.helloworld.utils.Resposta;
import com.example.helloworld.vo.BookVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/book")
	public ResponseEntity<Resposta> createBook(@RequestBody Book book) {
		Book bookSaved = bookService.save(book);
		BookVO bookVO = JsonUtil.jsonToObject(bookSaved.toString(), BookVO.class);
		Resposta resposta = new Resposta().setRetornoOK(bookVO);
		return ResponseEntity.ok(resposta);
	}

	@PostMapping("/books")
	public ResponseEntity<Resposta> createBooks(@RequestBody List<Book> bookList) {
		int count = 0;

		for (Book book : bookList) {
			Book bookSaved = bookService.save(book);
			if(bookSaved != null) {
				count++;
			}
		}

		Resposta resposta = new Resposta().setRetornoOK("Total of "+count+" Books were created");
		return ResponseEntity.ok(resposta);
	}

	@GetMapping("/book/{id}")
	public ResponseEntity<Resposta> getBookById(@PathVariable Long id) {
		Book book = bookService.findById(id);
		System.out.println(book.toString());
		BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);
		Resposta resposta = new Resposta().setRetornoOK(bookVO);
		return ResponseEntity.ok(resposta);
	}

	@GetMapping("/books")
	public ResponseEntity<Resposta> getBooks(@RequestParam(value = "title", required = false) String title) {
		List<Book> bookList = bookService.findAll(title);

		List<BookVO> bookVOList = new ArrayList<>();
		for (Book book : bookList) {
			bookVOList.add(JsonUtil.jsonToObject(book.toString(), BookVO.class));
		}

		Resposta resposta = new Resposta().setRetornoOK(bookVOList);
		return ResponseEntity.ok(resposta);
	}
	
	@DeleteMapping("/book")
	public ResponseEntity<Resposta> deleteBook(@RequestBody Book book) {
		bookService.delete(book);

		Resposta resposta = new Resposta().setRetornoOK();
		return ResponseEntity.ok(resposta);
	}

}
