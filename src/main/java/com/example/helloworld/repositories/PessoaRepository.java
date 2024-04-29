package com.example.helloworld.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.helloworld.models.Pessoa;

public interface PessoaRepository extends Repository<Pessoa, Long> {

	Pessoa save(Pessoa pessoa);
	Pessoa findById(Long id);
	List<Pessoa> findAll();
	List<Pessoa> findByFirstNameIgnoreCase(String firstName);
}
