package com.ufcg.si1.model;

import br.edu.ufcg.Hospital;

public class HospitalAdapter extends UnidadeSaude{
	
	private Hospital hospital;
	
    private float taxaDiariaAtendimentos;

    public HospitalAdapter(String descricao, int taxa, String nome, int medicos, float numAtendimentos) {
        super(nome);
        this.taxaDiariaAtendimentos = taxa;
        this.hospital =new Hospital(descricao, medicos, numAtendimentos);
    }
    

    public int getNumeroMedicos() {
    	return this.hospital.getNumeroMedicos();
    }
    
    public String getDescricao() {
    	return this.hospital.getDescricao();
    }
    
    public float numAtendimentos() {
    	return this.hospital.getNumeroPacientesDia();
    }
    

    public float getTaxaDiariaAtendimentos() {
        return taxaDiariaAtendimentos;
    }

    public void setTaxaDiariaAtendimentos(float taxaDiariaAtendimentos) {
        this.taxaDiariaAtendimentos = taxaDiariaAtendimentos;
    }
    
}
