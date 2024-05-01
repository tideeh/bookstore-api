package com.example.helloworld.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class Resposta {
	public static final String RETORNO_ERRO					= "-1";
	public static final String RETORNO_ALERTA				=  "0";
	public static final String RETORNO_OK					=  "1";

	public static final String MENSAGEM_RETORNO_OK			=  "success";

	private String codigo;
	private String mensagem;
	private Object resultado;
	private Object erro;

	public static Resposta setRetornoOK() {
		Resposta resposta = new Resposta();
		resposta.setCodigo(RETORNO_OK);
		resposta.setMensagem(MENSAGEM_RETORNO_OK);
		return resposta;
	}

	public static Resposta setRetornoOK(Object resultado) {
		Resposta resposta = new Resposta();
		resposta.setCodigo(RETORNO_OK);
		resposta.setResultado(resultado);
		resposta.setMensagem(MENSAGEM_RETORNO_OK);
		return resposta;
	}

	public static Resposta setRetornoOK(Object resultado, String mensagem) {
		Resposta resposta = new Resposta();
		resposta.setCodigo(RETORNO_OK);
		resposta.setResultado(resultado);
		resposta.setMensagem(mensagem);
		return resposta;
	}

	public static Resposta setRetornoERRO(String mensagem) {
		Resposta resposta = new Resposta();
		resposta.setCodigo(RETORNO_ERRO);
		resposta.setMensagem(mensagem);
		return resposta;
	}

	public static Resposta setRetornoERRO(Throwable throwable) {
		Resposta resposta = new Resposta();
		resposta.setCodigo(RETORNO_ERRO);
		resposta.setMensagem(throwable.getMessage());
		resposta.setErro(ExceptionUtils.getStackTrace(throwable));
		return resposta;
	}



	
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getResultado() {
		return this.resultado;
	}

	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}

	public Object getErro() {
		return this.erro;
	}

	public void setErro(Object erro) {
		this.erro = erro;
	}
}