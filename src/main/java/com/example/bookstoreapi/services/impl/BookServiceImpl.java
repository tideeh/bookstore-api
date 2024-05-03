package com.example.bookstoreapi.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.repositories.BookRepository;
import com.example.bookstoreapi.services.BookService;
import com.example.bookstoreapi.utils.exceptions.SearchNotFoundException;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		Book bookSaved = bookRepository.save(book);
		return bookSaved;
	}

	@Override
	public Book findById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(
					() -> new SearchNotFoundException("Book not found with the given ID.")
				);
	}

	@Override
	public List<Book> findAll(String title) {
		List<Book> bookList = new ArrayList<>();

		if (title == null || title.isEmpty() || title.isBlank()) {
			bookList = bookRepository.findAll();
		} else {
			bookList = bookRepository.findByTitleIgnoreCase(title);
		}

		return bookList;
	}

	@Override
	public void delete(Book book) {
		bookRepository.findById(book.getId())
			.orElseThrow(
				() -> new SearchNotFoundException("Book not found with the given ID.")
			);
		
		bookRepository.delete(book);
	}

	@Override
	public void deleteById(Long id) {
		bookRepository.findById(id)
			.orElseThrow(
				() -> new SearchNotFoundException("Book not found with the given ID.")
			);
		
		bookRepository.deleteById(id);
	}

}
