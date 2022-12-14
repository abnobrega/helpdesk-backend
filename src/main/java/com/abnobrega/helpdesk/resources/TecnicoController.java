package com.abnobrega.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abnobrega.helpdesk.domain.Tecnico;
import com.abnobrega.helpdesk.dtos.TecnicoDTO;
import com.abnobrega.helpdesk.services.TecnicoService;

//********************************************
//************** API de TECNICO **************
//********************************************
//Endpoint para acessar os serviços/recursos do técnico.
@RestController
@RequestMapping(value = "/api/tecnicos") 
public class TecnicoController {
	
	//@Value("${teste.mensagem}")
	//private String mensagem;
	
    // Essa classe é um controlador REST de técnico, que vai receber as requisições HTTP e 
	// vai se comunicar dentro da arquitetura REST. => Requisição http://localhost/api/tecnicos

    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************
	@Autowired
	private TecnicoService tecnicoService;
	
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	
    //*********************************
    //******* C O N S U L T A R *******
    //*********************************
	// Endpoints para consultar técnicos	
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

    //*****************************
    //******* E X C L U I R *******
    //*****************************
	// Endpoint para excluir um técnico	
	@PreAuthorize("hasAnyRole('ADMIN')")// RN012 => Somente o ADMIN pode excluir um técnico	
	@DeleteMapping(value = "/{id}")    // Endpoint que recebe o id na URL
	public ResponseEntity<TecnicoDTO> excluirTecnico(@PathVariable Integer id) {
		tecnicoService.excluirTecnico(id);
		return ResponseEntity.noContent().build();
	}
		
    //*****************************
    //******* I N C L U I R *******
    //*****************************	
	// Endpoint para incluir um novo técnico
	@PreAuthorize("hasAnyRole('ADMIN')") // RN012 => Somente o ADMIN pode incluir um técnico	
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
	
    //*********************************
    //******* A T U A L I Z A R *******
    //*********************************	
	// Endpoint para atualizar os dados de um técnico	
	@PreAuthorize("hasAnyRole('ADMIN')") // RN012 => Somente o ADMIN pode atualizar um técnico		
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> atualizarTecnico(@PathVariable Integer id,
													   @Valid @RequestBody TecnicoDTO objDto){
		Tecnico objAtualizado = tecnicoService.atualizarTecnico(id, objDto);

		// No corpo da resposta crio um novo tecnicoDTO passando o objAtualizado como parâmetro.
		return ResponseEntity.ok().body(new TecnicoDTO(objAtualizado));
	}

}
