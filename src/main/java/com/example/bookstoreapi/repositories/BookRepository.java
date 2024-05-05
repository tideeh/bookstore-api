package com.example.bookstoreapi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstoreapi.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Page<Book> findByTitleIgnoreCase(String title,Pageable pageable);

	List<Book> findByCategoryIgnoreCase(String category);
}
