package com.example.bookstoreapi.services;

import java.util.List;

import com.example.bookstoreapi.models.Book;

public interface BookService {

	Book save(Book book);

	Book findById(Long id);

	List<Book> findAll(String title, String order);

	void delete(Book book);

	void deleteById(Long id);
}
