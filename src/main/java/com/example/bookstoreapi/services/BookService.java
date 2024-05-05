package com.example.bookstoreapi.services;

import org.springframework.data.domain.Page;

import com.example.bookstoreapi.models.Book;

public interface BookService {

	Book save(Book book);

	Book findById(Long id);

	Page<Book> findAll(String title, String page, String size, String order);

	void delete(Book book);

	void deleteById(Long id);
}
