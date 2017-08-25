package com.ufcg.si1.service;

import java.util.Iterator;
import java.util.List;

import com.ufcg.si1.model.UnidadeSaude;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;


public interface UnidadeSaudeService {

	Iterator<UnidadeSaude> getIterator();

	Object findByBairro(String bairro);

	Object findByCodigo(int codigo);
	
	void saveUS(UnidadeSaude unidade) throws Rep, ObjetoJaExistenteException;

	boolean existe(int codigo);

	void salvaDados();

	List<UnidadeSaude> findAllUS();

	void updateUS(UnidadeSaude unidade);

	void deleteUSByCodigo(int codigo);

	void deleteAllUsers();

	List<Object> getAll();

}
