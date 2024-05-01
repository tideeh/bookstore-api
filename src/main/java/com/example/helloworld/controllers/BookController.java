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
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(value = {"", "/", "/{id}"})
	public ResponseEntity<Resposta> getBookById(@PathVariable(required = false) Long id, @RequestParam(value = "title", required = false) String title) {
		Resposta resposta;

		if(id != null) {
			Book book = bookService.findById(id);
			BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);
			resposta = Resposta.setRetornoOK(bookVO);
		} else {
			List<Book> bookList = bookService.findAll(title);
			List<BookVO> bookVOList = new ArrayList<>();
			for (Book book : bookList) {
				bookVOList.add(JsonUtil.jsonToObject(book.toString(), BookVO.class));
			}
			resposta = Resposta.setRetornoOK(bookVOList);
		}
		
		return ResponseEntity.ok(resposta);
	}

	@PostMapping(value = {"", "/"})
	public ResponseEntity<Resposta> createBook(@RequestBody Book book) {
		Book bookSaved = bookService.save(book);
		BookVO bookVO = JsonUtil.jsonToObject(bookSaved.toString(), BookVO.class);
		Resposta resposta = Resposta.setRetornoOK(bookVO);
		return ResponseEntity.ok(resposta);
	}

	@DeleteMapping(value = {"", "/"})
	public ResponseEntity<Resposta> deleteBook(@RequestBody Book book) {
		bookService.delete(book);

		Resposta resposta = Resposta.setRetornoOK();
		return ResponseEntity.ok(resposta);
	}

	@PostMapping("/create-many")
	public ResponseEntity<Resposta> createBooks(@RequestBody List<Book> bookList) {
		int count = 0;

		for (Book book : bookList) {
			Book bookSaved = bookService.save(book);
			if(bookSaved != null) {
				count++;
			}
		}

		Resposta resposta = Resposta.setRetornoOK("Total of "+count+" Books were created");
		return ResponseEntity.ok(resposta);
	}

}
