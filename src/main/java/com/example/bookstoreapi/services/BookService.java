package com.example.bookstoreapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.models.vo.BookVO;

public interface BookService {

	BookVO create(Book newEntity);

	BookVO findById(Long id);

	Page<BookVO> findAll(Pageable pageable);
	Page<BookVO> findByTitleContaining(String title, Pageable pageable);

	BookVO update(Book newEntity);

	void deleteById(Long id);
}
