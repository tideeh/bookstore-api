package com.example.helloworld.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.repositories.PessoaRepository;
import com.example.helloworld.utils.JsonUtil;
import com.example.helloworld.utils.vo.PessoaVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;
	
	@GetMapping("/hello")
	public String getHello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@PostMapping("/pessoa")
	public PessoaVO createPessoa(@RequestBody Pessoa pessoa) {
		
		pessoaRepository.save(pessoa);
		return JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class);
	}

	@GetMapping("/pessoa/{id}")
	public PessoaVO getPessoaById(@PathVariable Long id) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);

		PessoaVO pessoaVO = new PessoaVO();
		if( optionalPessoa.isPresent() ) {
			Pessoa pessoa = optionalPessoa.get();
			
			System.out.println(pessoa.getId());
			pessoaVO = JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class);
		}

		return pessoaVO;
	}

	@GetMapping("/pessoas")
	public List<PessoaVO> getPessoas(@RequestParam(value = "firstName", required = false) String firstName) {
		List<Pessoa> pessoas = new ArrayList<>();
		
		if(firstName == null) {
			pessoas = pessoaRepository.findAll();
		} else {
			pessoas = pessoaRepository.findByFirstNameIgnoreCase(firstName);
		}

		List<PessoaVO> listPessoasVO = new ArrayList<>();
		for (Pessoa pessoa : pessoas) {
			listPessoasVO.add(JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class));
		}

		return listPessoasVO;
	}
	
}
