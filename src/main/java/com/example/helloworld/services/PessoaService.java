package com.example.helloworld.services;

import java.util.List;

import com.example.helloworld.models.Pessoa;

public interface PessoaService {

	Pessoa save(Pessoa pessoa);

	Pessoa findById(Long id);

	List<Pessoa> findAll(String firstName);

	void delete(Pessoa pessoa);
}
