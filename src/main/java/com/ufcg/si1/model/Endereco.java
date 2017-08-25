package com.ufcg.si1.model;

import java.io.Serializable;

public class Endereco implements Serializable{

	private String rua;
	private String uf;
	private String cidade;

	public Endereco(){
		super();
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
	
	
	public void setCidade(String cidade){
		this.cidade= cidade;
	}
	
	public void setUf(String uf){
		this.uf= uf;
	}
	
	public void setRua(String rua){
		this.rua= rua;
	}
	
}
