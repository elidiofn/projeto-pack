package com.ufcg.si1.model;

public class QueixaAnimal extends Queixa{


	private String tipoDoAnimal;
	protected Endereco endereco;

	public QueixaAnimal(String tipoDoAnimal, long id, String descricao, int situacao, String comentario,
            String nome, String email,
			  String rua, String uf, String cidade){
		super(id,descricao,situacao,comentario, nome, email, rua, uf, cidade);
		this.tipoDoAnimal = tipoDoAnimal;
		this.endereco = new Endereco(rua,uf,cidade);
		this.solicitante = new Pessoa(nome, email);
	}
	
	public String getTipoDoAnimal() {
		return tipoDoAnimal;
	}

	public void setTipoDoAnimal(String tipoDoAnimal) {
		this.tipoDoAnimal = tipoDoAnimal;
	}
	
	
}
