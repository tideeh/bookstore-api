package com.example.helloworld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Table(name = "pessoa")
@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String lastName;
	private int idade;
	private String cpf;

	public Pessoa() {
	}

	public Pessoa(String firstName, String lastName, int idade, String cpf) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.idade = idade;
		this.cpf = cpf;
	}

	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", firstName='" + getFirstName() + "'" +
			", lastName='" + getLastName() + "'" +
			", idade='" + getIdade() + "'" +
			", cpf='" + getCpf() + "'" +
			"}";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Pessoa)) {
			return false;
		}
		Pessoa pessoa = (Pessoa) o;
		return Objects.equals(firstName, pessoa.firstName) && Objects.equals(lastName, pessoa.lastName) && idade == pessoa.idade && Objects.equals(cpf, pessoa.cpf);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, idade, cpf);
	}
	
}