package com.example.helloworld.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.models.Pessoa;
import com.example.helloworld.services.PessoaService;
import com.example.helloworld.utils.JsonUtil;
import com.example.helloworld.utils.Resposta;
import com.example.helloworld.vo.PessoaVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping("/pessoa")
	public ResponseEntity<Resposta> createPessoa(@RequestBody Pessoa pessoa) {
		Pessoa pessoaSaved = pessoaService.save(pessoa);
		PessoaVO pessoaVO = JsonUtil.jsonToObject(pessoaSaved.toString(), PessoaVO.class);
		Resposta resposta = new Resposta().setRetornoOK(pessoaVO);
		return ResponseEntity.ok(resposta);
	}

	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Resposta> getPessoaById(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.findById(id);
		PessoaVO pessoaVO = JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class);
		Resposta resposta = new Resposta().setRetornoOK(pessoaVO);
		return ResponseEntity.ok(resposta);
	}

	@GetMapping("/pessoas")
	public ResponseEntity<Resposta> getPessoas(@RequestParam(value = "firstName", required = false) String firstName) {
		List<Pessoa> pessoaList = pessoaService.findAll(firstName);

		List<PessoaVO> pessoaVOList = new ArrayList<>();
		for (Pessoa pessoa : pessoaList) {
			pessoaVOList.add(JsonUtil.jsonToObject(pessoa.toString(), PessoaVO.class));
		}

		Resposta resposta = new Resposta().setRetornoOK(pessoaVOList);
		return ResponseEntity.ok(resposta);
	}
	
	@DeleteMapping("/pessoa")
	public ResponseEntity<Resposta> deletePessoa(@RequestBody Pessoa pessoa) {
		pessoaService.delete(pessoa);

		Resposta resposta = new Resposta().setRetornoOK();
		return ResponseEntity.ok(resposta);
	}

}
