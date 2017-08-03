package com.ufcg.si1.model;

public class Endereco {

	private String rua;

	private String uf;

	private String cidade;

	public Endereco(){

	}

	public Endereco(String rua, String uf, String cidade) {
		this.rua = rua;
		this.uf = uf;
		this.cidade = cidade;
	}

	public String getRua() {
		return this.rua;
	}

	public String getUf() {
		return this.uf;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
}
