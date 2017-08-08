package com.ufcg.si1.model;


public class PostoSaude extends UnidadeSaude{
	
	
    private float taxaDiariaAtendimentos;

    public PostoSaude(String descricao, int taxa, String nome) {
        super(nome);
        this.taxaDiariaAtendimentos = taxa;
    }

 

    public float getTaxaDiariaAtendimentos() {
        return taxaDiariaAtendimentos;
    }

    public void setTaxaDiariaAtendimentos(float taxaDiariaAtendimentos) {
        this.taxaDiariaAtendimentos = taxaDiariaAtendimentos;
    }
}
