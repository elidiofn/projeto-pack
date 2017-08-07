package com.ufcg.si1.model;

public class Endereco {

	private String rua;

	private String uf;

	private String cidade;
	
	private String bairro;

	public Endereco(){

	}

	public Endereco(String rua, String uf, String cidade, String bairro) {
		this.rua = rua;
		this.bairro = bairro;
		this.uf = uf;
		this.cidade = cidade;
	}
	
	public Endereco(String rua, String bairro){
		this.rua = rua;
		this.bairro = bairro;
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
	
	public String getBairro(){
		return this.bairro;
	}
	
	
	public void setCidade(String cidade){
		this.cidade= cidade;
	}
	
	public void setUf(String uf){
		this.uf= uf;
	}
	
	public void setRua(String rua){
		this.rua= rua;
	}
	
	public void setBairro(String bairo){
		this.bairro= bairro;
	}
}
