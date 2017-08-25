package com.ufcg.si1.service;

import br.edu.ufcg.Hospital;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.model.UnidadeSaude;
import com.ufcg.si1.util.Deserializador;
import com.ufcg.si1.util.Serializador;

import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service("unidadeSaudeService")
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {

    private static List<UnidadeSaude> unidadesDeSaude;

    static {
        unidadesDeSaude = populateDummyUS();
        carregarDados();
    }
	
    public static void carregarDados(){
    	Deserializador d = new Deserializador();
    	try{
    		ArrayList<UnidadeSaude> lista= (ArrayList<UnidadeSaude>) d.deserializar("./src/arquivos/US.bd");
    		if(lista != null){
    			unidadesDeSaude =lista;
    		}
    	}catch (Exception e) {
    		System.out.println("Erro"+e.getMessage());
		}
    	
    }

    @Override
    public void salvaDados(){
    	Serializador s = new Serializador();
    	try {
			s.serializar("./src/arquivos/US.bd", unidadesDeSaude);
		} catch (Exception e) {
			System.out.println("Erro"+e.getMessage());
		}
    }
    
    private static List<UnidadeSaude> populateDummyUS() {
        List<UnidadeSaude> unidadesDeSaude = new ArrayList<UnidadeSaude>();

        unidadesDeSaude.add(new UnidadeSaude(unidadesDeSaude.size(), "Mini Trauma", "Rua dos doentes", "Bairro aquieagora"));

        return unidadesDeSaude;
    }
    
	@Override
	public List<Object> getAll() {
		return Arrays.asList(unidadesDeSaude);
	}
    
   
    
    @Override
    public List<UnidadeSaude> findAllUS() {
        return unidadesDeSaude;
    }
    
    @Override
    public void saveUS(UnidadeSaude unidade) throws Rep, ObjetoJaExistenteException {
    	
    	if (unidade == null){
    		throw new Rep("Erro!");
    	}
    	// ajeitar aqui depois, n é contains pq cada obj é um hashcode diferente
		if (unidadesDeSaude.contains(unidade)) {
			throw new ObjetoJaExistenteException("Objeto jah existe no array");
		}
		

        unidade.setCodigo(unidadesDeSaude.size());
        unidadesDeSaude.add(unidade);
        this.salvaDados();
    }
		
	@Override
	public boolean existe(int codigo) {
		boolean existe = false;
		for(Object o : unidadesDeSaude){
			if (o instanceof UnidadeSaude) {
				UnidadeSaude unidadeSaude = (UnidadeSaude) o;
				if (unidadeSaude.getCodigo() == codigo) {
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
    
	@Override
    public void updateUS(UnidadeSaude unidade) {
        int index = unidadesDeSaude.indexOf(unidade);
        unidadesDeSaude.set(index, unidade);
    }
    
	@Override
    public void deleteUSByCodigo(int codigo) {

        for (Iterator<UnidadeSaude> iterator = unidadesDeSaude.iterator(); iterator.hasNext(); ) {
            UnidadeSaude u = iterator.next();
            if (u.getCodigo() == codigo) {
                iterator.remove();
            }
        }
    }

    @Override
    public Iterator<UnidadeSaude> getIterator() {
        return unidadesDeSaude.iterator();
    }
    
    @Override
    public void deleteAllUsers() {
        unidadesDeSaude.clear();
    }
    
    
	@Override
	public Object findByBairro(String bairro) {
		for (Object esp : unidadesDeSaude) {
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
	
	@Override
	public Object findByCodigo(int codigo) {
		for (Object esp : unidadesDeSaude) {
			if (esp instanceof UnidadeSaude) {
				UnidadeSaude unidadeSaude = (UnidadeSaude) esp;
				if (unidadeSaude != null && unidadeSaude.getCodigo() == codigo) {
					return unidadeSaude;
				}
			} else if (esp instanceof Hospital) {
				Hospital hospital = (Hospital) esp;
				if (hospital != null && hospital.getCodigo() == codigo) {
					return hospital;
				}
			}
		}
		return null;
	}
	
}
