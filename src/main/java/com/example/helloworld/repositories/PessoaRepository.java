package com.example.helloworld.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helloworld.models.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	List<Pessoa> findByFirstNameIgnoreCase(String firstName);
}
