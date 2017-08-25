package com.ufcg.si1.controller;

import com.ufcg.si1.model.*;
import com.ufcg.si1.service.*;
import com.ufcg.si1.util.CustomErrorType;
import com.ufcg.si1.util.ObjWrapper;
import exceptions.ObjetoInexistenteException;
import exceptions.ObjetoInvalidoException;
import exceptions.ObjetoJaExistenteException;
import exceptions.Rep;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestApiController {

    QueixaService queixaService = new QueixaServiceImpl();
    EspecialidadeService especialidadeService = new EspecialidadeServiceImpl();
    UnidadeSaudeService unidadeSaudeService = new UnidadeSaudeServiceImpl();
	public static final int SITUACAO_PREFEITURA_NORMAL = 0;
	public static final int SITUACAO_PREFEITURA_EXTRA = 1;

    private int situacaoAtualPrefeitura = SITUACAO_PREFEITURA_NORMAL;


    // -------------------Retrieve All Complaints---------------------------------------------

    @RequestMapping(value = "/queixa/", method = RequestMethod.GET)
    public ResponseEntity<List<Queixa>> listAllUsers() {
        List<Queixa> queixas = queixaService.findAllQueixas();

        if (queixas.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Queixa>>(queixas, HttpStatus.OK);
    }

    // UNIDADE DE SAUDE
    
    @RequestMapping(value = "/unit/", method = RequestMethod.POST)
    public ResponseEntity<?> CriarUnidade(@RequestBody UnidadeSaude us, UriComponentsBuilder ucBuilder) {
    	try {
			unidadeSaudeService.saveUS(us);
		} catch (Rep | ObjetoJaExistenteException e) {
			return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
		}


        return new ResponseEntity<UnidadeSaude>(us, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/unit/{bairro}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarUnidade(@PathVariable("bairro") String bairro) {
    	
    	UnidadeSaude unit = (UnidadeSaude) unidadeSaudeService.findByBairro(bairro);
    	
        if (unit == null) {
            return new ResponseEntity(new CustomErrorType("Unidade with bairro " + bairro
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UnidadeSaude>(unit, HttpStatus.OK);
    }

    // QUEIXA

    @RequestMapping(value = "/queixa/", method = RequestMethod.POST)
    public ResponseEntity<?> abrirQueixa(@RequestBody Queixa queixa, UriComponentsBuilder ucBuilder) {
    	
        try {
            queixa.abrir();
        } catch (ObjetoInvalidoException e) {
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);
        }
        queixaService.saveQueixa(queixa);

        return new ResponseEntity<Queixa>(queixa, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/queixa/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> consultarQueixa(@PathVariable("id") long id) {

        Queixa q = queixaService.findById(id);
        if (q == null) {
            return new ResponseEntity(new CustomErrorType("Queixa with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Queixa>(q, HttpStatus.OK);
    }


    @RequestMapping(value = "/queixa/", method = RequestMethod.PUT)
    public ResponseEntity<Queixa> updateQueixa(@RequestBody Queixa queixa, UriComponentsBuilder ucBuilder) {
    	
        Queixa currentQueixa = queixaService.findById(queixa.getId());
        System.out.println(currentQueixa == null);
        System.out.println(currentQueixa.getSituacao());
        System.out.println(queixa.getSituacao());
        

        if (currentQueixa == null) {
            return new ResponseEntity(new CustomErrorType("Unable to upate. Queixa with id " + queixa.getId() + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentQueixa.setDescricao(queixa.getDescricao());
        currentQueixa.setComentario(queixa.getComentario());
        currentQueixa.setSolicitante(queixa.getSolicitante());
        currentQueixa.setEndereco(queixa.getEndereco());
        currentQueixa.setSituacao(queixa.getSituacao());

        queixaService.updateQueixa(currentQueixa);
        return new ResponseEntity<Queixa>(currentQueixa, HttpStatus.OK);
    }

    @RequestMapping(value = "/queixa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

        Queixa user = queixaService.findById(id);
        if (user == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Queixa with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        queixaService.deleteQueixaById(id);
        return new ResponseEntity<Queixa>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/queixa/fechamento", method = RequestMethod.POST)
    public ResponseEntity<?> fecharQueixa(@RequestBody Queixa queixaAFechar) {
        queixaAFechar.situacao = Queixa.FECHADA;
        queixaService.updateQueixa(queixaAFechar);
        return new ResponseEntity<Queixa>(queixaAFechar, HttpStatus.OK);
    }

// SITUACAO

    @RequestMapping(value = "/geral/situacao", method = RequestMethod.GET)
    public ResponseEntity<?> getSituacaoGeralQueixas() {

        // dependendo da situacao da prefeitura, o criterio de avaliacao muda
        // se normal, mais de 20% abertas eh ruim, mais de 10 eh regular
        // se extra, mais de 10% abertas eh ruim, mais de 5% eh regular
        if (situacaoAtualPrefeitura == SITUACAO_PREFEITURA_NORMAL) {
            if ((double) queixaService.numeroQueixasAbertas() / queixaService.findAllQueixas().size() > 0.2) {
                return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(0), HttpStatus.OK);
            } else {
                if ((double) queixaService.numeroQueixasAbertas() / queixaService.findAllQueixas().size() > 0.1) {
                    return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(1), HttpStatus.OK);
                }
            } 
        }
        if (this.situacaoAtualPrefeitura == SITUACAO_PREFEITURA_EXTRA) {
            if ((double) queixaService.numeroQueixasAbertas() / queixaService.findAllQueixas().size() > 0.1) {
                return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(0), HttpStatus.OK);
            } else {
                if ((double) queixaService.numeroQueixasAbertas() / queixaService.findAllQueixas().size() > 0.05) {
                    return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(1), HttpStatus.OK);
                }
            }
        }

        //situacao retornada
        //0: RUIM
        //1: REGULAR
        //2: BOM
        return new ResponseEntity<ObjWrapper<Integer>>(new ObjWrapper<Integer>(2), HttpStatus.OK);
    }

    @RequestMapping(value="/unidade/busca", method= RequestMethod.GET)
    public ResponseEntity<?> consultarUnidadeSaudePorBairro(@RequestParam(value = "bairro", required = true) String bairro){
        Object us = unidadeSaudeService.findByBairro(bairro);
        if (us == null && !(us instanceof UnidadeSaude)) {
            return new ResponseEntity(new CustomErrorType("Unidade with bairro " + bairro
                    + " not found"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UnidadeSaude>((UnidadeSaude) us, HttpStatus.OK);
    }

   

}

