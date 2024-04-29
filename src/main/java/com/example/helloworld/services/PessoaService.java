package com.example.helloworld.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.repositories.PessoaRepository;
import com.example.helloworld.utils.JsonUtil;
import com.example.helloworld.vo.PessoaVO;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	public PessoaVO createPessoa(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class);
	}

	public PessoaVO getPessoaById(Long id) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);

		PessoaVO pessoaVO = new PessoaVO();
		if( optionalPessoa.isPresent() ) {
			Pessoa pessoa = optionalPessoa.get();
			
			System.out.println(pessoa.getId());
			pessoaVO = JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class);
		}

		return pessoaVO;
	}

	public List<PessoaVO> getPessoas(String firstName) {
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
