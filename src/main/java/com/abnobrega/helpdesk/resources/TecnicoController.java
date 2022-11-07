package com.abnobrega.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> lista = tecnicoService.ListarTecnicos();
		List<TecnicoDTO> listaDTO = lista.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO); // No corpo da resposta retorno uma lista.
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> incluirTecnico(@Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico newObj = tecnicoService.inluirTecnico(objDTO);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		/* Quando crio um novo objeto no BD ele recebe um id. Logo, é importante eu 
		 * retornar para o usuário, na aplicação cliente, a (URL = URI) de acesso a 
		 * esse novo objeto. Isto é como se estivéssemos retornando o id do objeto 
		 * criado para o usuário.
		 */
	}

}
