package com.example.bookstoreapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.bookstoreapi.models.Book;
import com.example.bookstoreapi.models.vo.BookVO;
import com.example.bookstoreapi.repositories.BookRepository;
import com.example.bookstoreapi.services.BookService;
import com.example.bookstoreapi.utils.JsonUtil;
import com.example.bookstoreapi.utils.exceptions.RequiredObjectIsNullException;
import com.example.bookstoreapi.utils.exceptions.SearchNotFoundException;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Override
	public BookVO create(Book newEntity) {
		if(newEntity == null)
            throw new RequiredObjectIsNullException("It is not allowed to create a null "+Book.class.getSimpleName()+"!");
		
		Book entitySaved = repository.save(newEntity);

		BookVO entityVO = JsonUtil.jsonToObject(entitySaved.toString(), BookVO.class);
		return entityVO;
	}

	@Override
	public BookVO findById(Long id) {
		Book entity = repository.findById(id)
			.orElseThrow(
				() -> new SearchNotFoundException(Book.class.getSimpleName()+" not found with the given ID!")
			);

		BookVO entityVO = JsonUtil.jsonToObject(entity.toString(), BookVO.class);
		return entityVO;
	}

	@Override
	public Page<BookVO> findAll(Pageable pageable) {
		Page<Book> pageEntity = repository.findAll(pageable);
		
		Page<BookVO> pageEntityVO = pageEntity.map((entity) -> {
			BookVO entityVO = JsonUtil.jsonToObject(entity.toString(), BookVO.class);
			return entityVO;
		});

		return pageEntityVO;
	}

	@Override
	public Page<BookVO> findByTitleContaining(String title, Pageable pageable) {
		Page<Book> pageEntity = repository.findByTitleContaining(title, pageable);
		
		Page<BookVO> pageEntityVO = pageEntity.map((entity) -> {
			BookVO entityVO = JsonUtil.jsonToObject(entity.toString(), BookVO.class);
			return entityVO;
		});

		return pageEntityVO;
	}

	@Override
	public BookVO update(Book newEntity) {
		if(newEntity == null)
            throw new RequiredObjectIsNullException("It is not allowed to update a null "+Book.class.getSimpleName()+"!");

		Book entity = repository.findById(newEntity.getId())
			.orElseThrow(
				() -> new SearchNotFoundException(Book.class.getSimpleName()+" not found with the given ID!")
			);
		
		entity.setTitle(newEntity.getTitle());
		entity.setAuthor(newEntity.getAuthor());
		entity.setCategory(newEntity.getCategory());
		entity.setLanguage(newEntity.getLanguage());
		entity.setPrice(newEntity.getPrice());
		entity = repository.save(entity);

		BookVO entityVO = JsonUtil.jsonToObject(entity.toString(), BookVO.class);
		return entityVO;
	}

	@Override
	public void deleteById(Long id) {
		repository.findById(id)
			.orElseThrow(
				() -> new SearchNotFoundException(Book.class.getSimpleName()+" not found with the given ID!")
			);
		
		repository.deleteById(id);
	}

}
