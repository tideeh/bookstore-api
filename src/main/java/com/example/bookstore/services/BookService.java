package com.example.bookstore.services;

import java.util.List;

import com.example.bookstore.models.Book;

public interface BookService {

	Book save(Book book);

	Book findById(Long id);

	List<Book> findAll(String title);

	void delete(Book book);
}
