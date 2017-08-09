package com.ufcg.si1.util;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Serializador {

	public Serializador(){}
	
	public void serializar(String path, List lista) throws Exception{
		FileOutputStream out = new FileOutputStream(path);
		ObjectOutputStream objOut = new ObjectOutputStream(out);
		
		objOut.writeObject(lista);
		objOut.close();
	}
}
