package br.com.rickicollab.ediaristas.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rickicollab.ediaristas.api.dtos.response.DiaristaLocalidadesPagedResponse;
import br.com.rickicollab.ediaristas.api.dtos.response.DisponibilidadeResponse;
import br.com.rickicollab.ediaristas.api.services.ApiDiaristaService;

@RestController
@RequestMapping("api/diaristas")
public class DiaristasRestController {

   @Autowired
   private ApiDiaristaService service;
   
   @GetMapping("/localidades")
   public DiaristaLocalidadesPagedResponse buscarDiaristasPorCep(@RequestParam(required = false) String cep){
         
         return service.buscarDiaristasPorCep(cep);
   }


   @GetMapping("/disponibilidade")
   public DisponibilidadeResponse verificarDisponibilidadePorCep(@RequestParam(required = false) String cep){
      return service.verificarDisponibilidadePorCep(cep);
   }







    
}