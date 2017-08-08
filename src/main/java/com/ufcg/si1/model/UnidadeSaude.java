package com.ufcg.si1.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PostoSaude.class, name = "posto")
})
public class UnidadeSaude {
    private int codigo;

    private Endereco endereco;
    
    private String nome;
    

    private List especialidades = new ArrayList();

    private long [] numeroQueixas = new long[1000];
    int contador = 0;

    public UnidadeSaude(String nome) {
        this.codigo = 0; // gerado no repositorio
        this.setNome(nome);
    }
    public UnidadeSaude(String nome, String rua, String bairro){
    	
    	this.endereco = new Endereco(rua, bairro);
    	this.setNome(nome);
    	
    	
    }

	public void addQueixaProxima(long id) {
        if (this instanceof PostoSaude){
            numeroQueixas[contador++] = id;
        }
    }
	
	public String getBairro() {
		return endereco.getBairro();
	}
	
	public void setRua(String rua) {
		endereco.setRua(rua);
	}
	
	public void setBairro(String bairro) {
		endereco.setRua(bairro);
	}

 
    public List<Especialidade> getEspecialidades() {
        return this.especialidades;
    }

    public void adicionarEspecialidade(Especialidade esp) {
        this.especialidades.add(esp);
    }

    public int pegaCodigo() {
        return this.codigo;
    }

    public void mudaCodigo(int cod) {
        this.codigo = cod;
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
