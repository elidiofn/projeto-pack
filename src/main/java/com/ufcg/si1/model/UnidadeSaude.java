package com.ufcg.si1.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UnidadeSaude implements Serializable{
    private int codigo;

    private String rua;
    private String bairro;
    private String nome; 

    private List especialidades = new ArrayList();

    private long [] numeroQueixas = new long[1000];
    int contador = 0;

   public UnidadeSaude() {
       
    }
    
    public UnidadeSaude(String nome) {
        this.codigo = 0; // gerado no repositorio
        this.setNome(nome);
    }
    public UnidadeSaude(int codigo, String nome, String rua, String bairro){
    	
    	this.codigo = codigo;
    	this.nome = nome;
    	this.rua = rua;
    	this.bairro = bairro;
    	
    }
	
	public String getBairro() {
		return this.bairro;
	}
	
	public void setRua(String rua) {
		this.rua=rua;
	}
	
	public void setBairro(String bairro) {
		this.bairro=bairro;
	}

	public String getRua(){
		return this.rua;
	}
    public List<Especialidade> getEspecialidades() {
        return this.especialidades;
    }

    public void adicionarEspecialidade(Especialidade esp) {
        this.especialidades.add(esp);
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int cod) {
        this.codigo = cod;
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
