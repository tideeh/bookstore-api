package com.example.bookstoreapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstoreapi.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findAllByOrderByIdDesc();
	List<Book> findAllByOrderByIdAsc();

	List<Book> findByTitleIgnoreCase(String title);
	List<Book> findByTitleIgnoreCaseOrderByIdDesc(String title);
	List<Book> findByTitleIgnoreCaseOrderByIdAsc(String title);

	List<Book> findByCategoryIgnoreCase(String category);
}
