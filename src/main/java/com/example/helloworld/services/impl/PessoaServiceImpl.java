package com.example.helloworld.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.repositories.PessoaRepository;
import com.example.helloworld.services.PessoaService;
import com.example.helloworld.utils.exceptions.PessoaNotFoundException;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public Pessoa save(Pessoa pessoa) {
		Pessoa pessoaSaved = pessoaRepository.save(pessoa);
		return pessoaSaved;
	}

	@Override
	public Pessoa findById(Long id) {
		return pessoaRepository.findById(id)
				.orElseThrow(
					() -> new PessoaNotFoundException("Pessoa not found with the given ID.")
				);
	}

	@Override
	public List<Pessoa> findAll(String firstName) {
		List<Pessoa> pessoaList = new ArrayList<>();

		if (firstName == null) {
			pessoaList = pessoaRepository.findAll();
		} else {
			pessoaList = pessoaRepository.findByFirstNameIgnoreCase(firstName);
		}

		return pessoaList;
	}

	@Override
	public void delete(Pessoa pessoa) {
		pessoaRepository.findById(pessoa.getId())
			.orElseThrow(
				() -> new PessoaNotFoundException("Pessoa not found with the given ID.")
			);
		
		pessoaRepository.delete(pessoa);
	}

}
