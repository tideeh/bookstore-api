package com.example.helloworld.services;

import java.util.List;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.vo.PessoaVO;

public interface PessoaService {

	PessoaVO save(Pessoa pessoa);

	PessoaVO findById(Long id);

	List<PessoaVO> findAll(String firstName);

	boolean delete(Pessoa pessoa);
}
