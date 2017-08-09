package com.ufcg.si1.prefeitura;

public enum SituacaoPrefeitura {
		NORMAL(0),
		EXTRA(1),
		CAOS(2);
	
	private int flag;
	
	private SituacaoPrefeitura(int flag){
		this.flag = flag;
	}
	
	public int getFlag(){
		return this.flag;
	}
}
