package com.example.helloworld.services;

import java.util.List;

import com.example.helloworld.models.Book;

public interface BookService {

	Book save(Book book);

	Book findById(Long id);

	List<Book> findAll(String title);

	void delete(Book book);
}
