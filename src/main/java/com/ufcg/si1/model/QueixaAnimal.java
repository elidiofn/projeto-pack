package com.ufcg.si1.model;

public class QueixaAnimal extends Queixa{

	private String tipoDoAnimal;

	public QueixaAnimal(String tipoDoAnimal, long id, String descricao, int situacao, String comentario,
            String nome, String email,
			  String rua, String uf, String cidade){
		super(id,descricao,situacao,comentario, nome, email, rua, uf, cidade);
		this.tipoDoAnimal = tipoDoAnimal;
		Endereco endereco = new Endereco();
		endereco.setRua(rua);
		endereco.setUf(uf);
		endereco.setCidade(cidade);
		this.solicitante = new Pessoa(nome, email, endereco);	
	}
	
	public String getTipoDoAnimal() {
		return tipoDoAnimal;
	}

	public void setTipoDoAnimal(String tipoDoAnimal) {
		this.tipoDoAnimal = tipoDoAnimal;
	}
	
}
