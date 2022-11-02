package com.abnobrega.helpdesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abnobrega.helpdesk.domain.Tecnico;
import com.abnobrega.helpdesk.dtos.TecnicoDTO;
import com.abnobrega.helpdesk.service.TecnicoService;

//********************************************
//************** API de TECNICO **************
//********************************************

@RestController
@RequestMapping(value = "/api/tecnicos") // Endpoint para acessar os serviços/recursos do técnico.
public class TecnicoController {
    // Essa classe é um controlador REST de técnico, que vai receber as requisições HTTP e 
	// vai se comunicar dentro da arquitetura REST. => Requisição http://localhost/api/tecnicos

    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************
	@Autowired
	private TecnicoService tecnicoService;
	
    //***********************************************
    //************** C O N S U L T A R **************
    //***********************************************
	@GetMapping(value = "/{id}")
	// localhost/api/tecnicos/id	
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		
		Tecnico obj = tecnicoService.findById(id);
		// Retornar o TecnicoDTO para o cliente.
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
				
	}

}
