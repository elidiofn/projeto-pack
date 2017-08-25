package com.ufcg.si1.service;

import com.ufcg.si1.model.Queixa;
import com.ufcg.si1.util.Deserializador;
import com.ufcg.si1.util.Serializador;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service("queixaService")
public class QueixaServiceImpl implements QueixaService {

    private static List<Queixa> queixas;

    static {
        queixas = populateDummyQueixas();
        carregarDados();
    }

    public static void carregarDados(){
    	Deserializador d = new Deserializador();
    	try{
    		ArrayList<Queixa> lista= (ArrayList<Queixa>) d.deserializar("./src/arquivos/queixas.bd");
    		if(lista != null){
    			queixas = lista;
    		}
    	}catch (Exception e) {
    		System.out.println("Erro"+e.getMessage());
		}
    	
    }
    public void salvaDados(){
    	Serializador s = new Serializador();
    	try {
			s.serializar("./src/arquivos/queixas.bd", queixas);
		} catch (Exception e) {
			System.out.println("Erro"+e.getMessage());
		}
    }
    
    private static List<Queixa> populateDummyQueixas() {
        List<Queixa> queixas = new ArrayList<Queixa>();

        queixas.add(new Queixa(queixas.size(), "Passei mal com uma coxinha",
                Queixa.FECHADA, "", "Jose Silva",
                "jose@gmail.com", "rua dos tolos", "PE", "Recife"));


        queixas.add(new Queixa(queixas.size(),
                "Bacalhau estragado, passamos mal!", Queixa.FECHADA, "",
                "Ailton Sousa", "ailton@gmail.com", "rua dos bobos", "PB",
                "Joao Pessoa"));

        queixas.add(new Queixa(queixas.size(), "Nossa rua estah muito suja", Queixa.FECHADA, "",
                "Jose Silva", "jose@gmail.com", "rua dos tolos", "PE", "Recife"));


        queixas.add(new Queixa(queixas.size(), "iluminacao horrivel, muitos assaltos", Queixa.FECHADA, "",
                "Ailton Sousa", "ailton@gmail.com", "rua dos bobos", "PB",
                "Joao Pessoa"));

        return queixas;
    }

    public List<Queixa> findAllQueixas() {
        return queixas;
    }

    public void saveQueixa(Queixa queixa) {
        queixa.setId(queixas.size());
        queixas.add(queixa);
        this.salvaDados();
    }

    public void updateQueixa(Queixa queixa) {
        int index = queixas.indexOf(queixa);
        queixas.set(index, queixa);
    }

    public void deleteQueixaById(long id) {

        for (Iterator<Queixa> iterator = queixas.iterator(); iterator.hasNext(); ) {
            Queixa q = iterator.next();
            if (q.getId() == id) {
                iterator.remove();
            }
        }
    }

    @Override
    public Iterator<Queixa> getIterator() {
        return queixas.iterator();
    }

    public void deleteAllUsers() {
        queixas.clear();
    }

    public Queixa findById(long id) {
        for (Queixa queixa : queixas) {
            if (queixa.getId() == id) {
                return queixa;
            }
        }
        return null;
    }
    
    public double numeroQueixasAbertas() {
        int contador = 0;
        for (Iterator<Queixa> it1 = getIterator() ; it1.hasNext(); ) {
            Queixa q = it1.next();
            if (q.getSituacao() == Queixa.ABERTA)
                contador++;
        }

        return contador;
    }




}
