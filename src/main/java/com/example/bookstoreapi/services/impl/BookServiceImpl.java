package com.example.bookstoreapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.repositories.BookRepository;
import com.example.bookstoreapi.services.BookService;
import com.example.bookstoreapi.utils.NumberUtil;
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
	public Page<Book> findAll(String title, String page, String size, String order) {
		long totalBooks = bookRepository.count();

		int pageInt = NumberUtil.getIntOrValue(page, 0);
		pageInt = pageInt < 0 ? 0 : pageInt;
		int sizeInt = NumberUtil.getIntOrValue(size, (int) totalBooks);
		sizeInt = sizeInt < 1 ? 1 : sizeInt;

		Pageable pageable = PageRequest.of(pageInt, sizeInt, order.equals("desc") ? Sort.by("id").descending() : Sort.by("id").ascending());
		
		Page<Book> pageBooks;
		if (title == null || title.isEmpty() || title.isBlank()) {
			pageBooks = bookRepository.findAll(pageable);
		} else {
			pageBooks = bookRepository.findByTitleIgnoreCase(title, pageable);
		}

		return pageBooks;
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
