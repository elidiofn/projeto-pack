package com.ufcg.si1.model;

import java.io.Serializable;

public class Pessoa implements Serializable{
	private String nome;
	private Endereco endereço;
	private String email;

	public Pessoa(){
		super();
	}

	public Pessoa(String nome, String email, Endereco endereco) {
		this.endereço = endereco;
		this.nome = nome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereço() {
		return endereço;
	}

	public void setEndereço(Endereco endereço) {
		this.endereço = endereço;
	}
	
	

}
