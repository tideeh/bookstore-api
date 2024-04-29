package com.example.helloworld.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.repositories.PessoaRepository;
import com.example.helloworld.services.PessoaService;
import com.example.helloworld.utils.JsonUtil;
import com.example.helloworld.vo.PessoaVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PessoaController {

	@Autowired
	PessoaService pessoaService;

	@Autowired
	PessoaRepository pessoaRepository;

	@PostMapping("/pessoa")
	public PessoaVO createPessoa(@RequestBody Pessoa pessoa) {
		return pessoaService.createPessoa(pessoa);
	}

	@GetMapping("/pessoa/{id}")
	public PessoaVO getPessoaById(@PathVariable Long id) {
		return pessoaService.getPessoaById(id);
	}

	@GetMapping("/pessoas")
	public List<PessoaVO> getPessoas(@RequestParam(value = "firstName", required = false) String firstName) {
		return pessoaService.getPessoas(firstName);
	}

}
