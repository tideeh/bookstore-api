package com.example.helloworld.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.services.PessoaService;
import com.example.helloworld.vo.PessoaVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping("/pessoa")
	public PessoaVO createPessoa(@RequestBody Pessoa pessoa) {
		return pessoaService.save(pessoa);
	}

	@GetMapping("/pessoa/{id}")
	public PessoaVO getPessoaById(@PathVariable Long id) {
		return pessoaService.findById(id);
	}

	@GetMapping("/pessoas")
	public List<PessoaVO> getPessoas(@RequestParam(value = "firstName", required = false) String firstName) {
		return pessoaService.findAll(firstName);
	}
	
	@DeleteMapping("/pessoa")
	public String deletePessoa(@RequestBody Pessoa pessoa) {
		if(pessoaService.delete(pessoa)) {
			return "Success";
		}
		return "Failed to delete Pessoa";
	}

}
