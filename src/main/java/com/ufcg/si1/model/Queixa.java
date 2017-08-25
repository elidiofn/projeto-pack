package com.ufcg.si1.model;

import java.io.Serializable;

import exceptions.ObjetoInvalidoException;

public class Queixa implements Serializable{

	private long id;

	private String flag = "alimenticia";
	private String descricao;

	protected Pessoa solicitante;
	protected Endereco endereco;

	public int situacao; 
	
	public static final int ABERTA = 1;
	public static final int EM_ANDAMENTO = 2;
	public static final int FECHADA = 3;

	private String comentario = "";

	public Queixa(long id, String descricao, int situacao, String comentario,
                  String nome, String email,
				  String rua, String uf, String cidade) {
		this.id = id;
		this.descricao = descricao;
		this.situacao = situacao;
		this.comentario = comentario;
		this.endereco = new Endereco(rua,uf,cidade);
		this.solicitante = new Pessoa(nome, email);
	}
	
	public Queixa(){
		id = 0;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getSituacao() {
		return situacao;
	}

	public void abrir() throws ObjetoInvalidoException {
		if (this.situacao != Queixa.EM_ANDAMENTO)
			this.situacao = Queixa.ABERTA;
		else
			throw new ObjetoInvalidoException("Status inválido");
	}

	public void fechar(String coment) throws ObjetoInvalidoException {
		if (this.situacao == Queixa.EM_ANDAMENTO
				|| this.situacao == Queixa.ABERTA) {
			this.situacao = Queixa.FECHADA;
			this.comentario = coment;
		} else
			throw new ObjetoInvalidoException("Status Inválido");
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pessoa getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Pessoa solicitante) {
		this.solicitante = solicitante;
	}
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Queixa other = (Queixa) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
