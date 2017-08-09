package com.ufcg.si1.service;

import br.edu.ufcg.Hospital;
import com.ufcg.si1.model.UnidadeSaude;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("unidadeSaudeService")
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {
	private Object[] vetor;
	private List<Object> lista;
	private int indice;

	private int geraCodigo = 0; // para gerar codigos das queixas cadastradas

	public UnidadeSaudeServiceImpl() {
		lista = new ArrayList<>();
		vetor = new Object[100];
		indice = 0;
	}

	@Override
	public Object procura(int codigo) throws Rep, ObjetoInexistenteException {
		int i = 0;
		while (i < indice) {
			if (lista.get(i) instanceof UnidadeSaude) {
				UnidadeSaude unidadeSaude = (UnidadeSaude) lista.get(i);
				if (unidadeSaude.pegaCodigo() == codigo) {
					return lista.get(i);
				}
			} else if (lista.get(i) instanceof Hospital) {
				Hospital hospital = (Hospital) lista.get(i);
				if (hospital.getCodigo() == codigo) {
					return lista.get(i);
				}
			}
			i++;
		}
		throw new ObjetoInexistenteException("Não achou unidade");
	}

	@Override
	public List<Object> getAll() {
		return Arrays.asList(vetor);
	}

	@Override
	public void insere(Object us) throws Rep, ObjetoJaExistenteException {

		if (us == null) {
			throw new Rep("Erro!");
		} else {
			if (us instanceof UnidadeSaude) {
				((UnidadeSaude) us).mudaCodigo(++geraCodigo);
			} else {
				((Hospital) us).setCodigo(++geraCodigo);
			}
		}
/*
		if (indice == this.vetor.length) {
			throw new Rep("Erro ao incluir no array");
		}
*/
		if (us instanceof UnidadeSaude) {
			UnidadeSaude unidadeSaude = (UnidadeSaude) us;
			if (this.existe(unidadeSaude.pegaCodigo())) {
				throw new ObjetoJaExistenteException("Objeto jah existe no array");
			}
		} else if (us instanceof Hospital) {
			Hospital hospital = (Hospital) us;
			if (this.existe(hospital.getCodigo())) {
				throw new ObjetoJaExistenteException("Objeto jah existe no array");
			}
		}
		this.lista.add(us);
/*
		this.vetor[indice] = us;
		indice++;*/
	}

	@Override
	public boolean existe(int codigo) {
		boolean existe = false;

		//for (int i = 0; i < indice; i++) {
		for(Object o : lista){
			if (o instanceof UnidadeSaude) {
				UnidadeSaude unidadeSaude = (UnidadeSaude) o;
				if (unidadeSaude.pegaCodigo() == codigo) {
					existe = true;
					break;
				}
			} else if (o instanceof Hospital) {
				Hospital hospital = (Hospital) o;
				if (hospital.getCodigo() == codigo) {
					existe = true;
					break;
				}
			}
		}

		return existe;
	}

	public Object findById(long id) {
		for (Object esp : lista) {
			if (esp instanceof UnidadeSaude) {
				UnidadeSaude unidadeSaude = (UnidadeSaude) esp;
				if (unidadeSaude != null && unidadeSaude.pegaCodigo() == id) {
					return unidadeSaude;
				}
			} else if (esp instanceof Hospital) {
				Hospital hospital = (Hospital) esp;
				if (hospital != null && hospital.getCodigo() == id) {
					return hospital;
				}
			}
		}
		return null;
	}

	@Override
	public Object findByBairro(String bairro) {
		for (Object esp : lista) {
			if (esp instanceof UnidadeSaude) {
				;
				UnidadeSaude u = (UnidadeSaude) esp;
				if (u.getBairro().equals(bairro)) {
					return esp;
				}
			} else if (esp instanceof Hospital) {
				Hospital h = (Hospital) esp;
				if (h.getDescricao().equals(bairro)) {
					return esp;
				}
			}
		}
		return null;
	}
}
