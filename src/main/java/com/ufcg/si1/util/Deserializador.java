package com.ufcg.si1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class Deserializador {
	
	public Deserializador(){}
	
	public List deserializar(String path) throws Exception{
		List lista = null;
		if(new File(path).canRead() == true){
			FileInputStream inp = new FileInputStream(path);
			ObjectInputStream objInp = new ObjectInputStream(inp);
			lista = (List) objInp.readObject();
			objInp.close();
		}
		return lista;
	}
}
