package com.ufcg.si1.model;


public class PostoSaude extends UnidadeSaude{
	
	private String nome;
    private float taxaDiariaAtendimentos;

    public PostoSaude(String descricao, int at, int taxa, String nome) {
        super(descricao);
        this.nome = nome;
        this.taxaDiariaAtendimentos = taxa;
    }

    public PostoSaude(){
        super();
    }

    public String getNome(){
    	return nome;
    }
    
    public void setNome(String nome){
    	this.nome = nome;
    }
    
    // implementacoes vazias

    public float taxaDiaria() {
        return taxaDiariaAtendimentos;
    }

    public float getTaxaDiariaAtendimentos() {
        return taxaDiariaAtendimentos;
    }

    public void setTaxaDiariaAtendimentos(float taxaDiariaAtendimentos) {
        this.taxaDiariaAtendimentos = taxaDiariaAtendimentos;
    }
}
