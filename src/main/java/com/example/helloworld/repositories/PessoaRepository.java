package com.example.helloworld.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helloworld.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	// Pessoa save(Pessoa pessoa);
	// Optional<Pessoa> findById(Long id);
	// List<Pessoa> findAll();
	List<Pessoa> findByFirstNameIgnoreCase(String firstName);
}
