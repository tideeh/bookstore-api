package com.example.helloworld.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.repositories.PessoaRepository;
import com.example.helloworld.services.PessoaService;
import com.example.helloworld.utils.JsonUtil;
import com.example.helloworld.vo.PessoaVO;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public PessoaVO save(Pessoa pessoa) {
		Pessoa pessoaSaved = pessoaRepository.save(pessoa);
		return JsonUtil.jsonToObject(pessoaSaved.toString(), PessoaVO.class);
	}

	@Override
	public PessoaVO findById(Long id) {
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow();
		PessoaVO pessoaVO = JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class);
		return pessoaVO;
	}

	@Override
	public List<PessoaVO> findAll(String firstName) {
		List<Pessoa> pessoas = new ArrayList<>();

		if (firstName == null) {
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

	@Override
	public boolean delete(Pessoa pessoa) {
		if (pessoaRepository.findById(pessoa.getId()).isPresent()) {
			pessoaRepository.delete(pessoa);
			return true;
		} else {
			return false;
		}
	}

}
