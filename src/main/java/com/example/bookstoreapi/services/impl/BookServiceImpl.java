package com.example.bookstoreapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.repositories.BookRepository;
import com.example.bookstoreapi.services.BookService;
import com.example.bookstoreapi.utils.JsonUtil;
import com.example.bookstoreapi.utils.exceptions.RequiredObjectIsNullException;
import com.example.bookstoreapi.utils.exceptions.SearchNotFoundException;
import com.example.bookstoreapi.vo.BookVO;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public BookVO create(Book newBook) {
		if(newBook == null)
            throw new RequiredObjectIsNullException("It is not allowed to create a null object!");
		
		Book bookSaved = bookRepository.save(newBook);

		BookVO bookVO = JsonUtil.jsonToObject(bookSaved.toString(), BookVO.class);
		return bookVO;
	}

	@Override
	public BookVO findById(Long id) {
		Book book = bookRepository.findById(id)
			.orElseThrow(
				() -> new SearchNotFoundException("Book not found with the given ID.")
			);

		BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);
		return bookVO;
	}

	@Override
	public Page<BookVO> findAll(Pageable pageable) {
		Page<Book> pageBooks = bookRepository.findAll(pageable);
		
		Page<BookVO> pageBooksVO = pageBooks.map((book) -> {
			BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);
			return bookVO;
		});

		return pageBooksVO;
	}

	@Override
	public Page<BookVO> findByTitleContaining(String title, Pageable pageable) {
		Page<Book> pageBooks = bookRepository.findByTitleContaining(title, pageable);
		
		Page<BookVO> pageBooksVO = pageBooks.map((book) -> {
			BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);
			return bookVO;
		});

		return pageBooksVO;
	}

	@Override
	public BookVO update(Book newBook) {
		if(newBook == null)
            throw new RequiredObjectIsNullException("It is not allowed to update a null object!");

		Book book = bookRepository.findById(newBook.getId())
			.orElseThrow(
				() -> new SearchNotFoundException("Book not found with the given ID.")
			);
		
		book.setTitle(newBook.getTitle());
		book.setAuthor(newBook.getAuthor());
		book.setCategory(newBook.getCategory());
		book.setLanguage(newBook.getLanguage());
		book.setPrice(newBook.getPrice());
		book = bookRepository.save(book);

		BookVO bookVO = JsonUtil.jsonToObject(book.toString(), BookVO.class);
		return bookVO;
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
