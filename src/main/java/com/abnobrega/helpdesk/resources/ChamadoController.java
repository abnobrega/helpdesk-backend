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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abnobrega.helpdesk.domain.Chamado;
import com.abnobrega.helpdesk.dtos.ChamadoDTO;
import com.abnobrega.helpdesk.services.ChamadoService;

//********************************************
//************** API de CHAMADO **************
//********************************************
//Endpoint incial para acessar os serviços/recursos do chamado.
@RestController
@RequestMapping(value = "/api/chamados")
public class ChamadoController {

    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************
	@Autowired
	private ChamadoService chamadoService;
	
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	
    //*********************************
    //******* C O N S U L T A R *******
    //*********************************
	// Endpoints para consultar Chamado pelo id		
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado obj = chamadoService.findById(id);
		// Retornar um ResponseEntity, onde ok(Fiz a requisição) e no body dessa resposta 
		// retorno um objeto chamadoDTO, usando o construtor que passa como parâmetro um chamado.		
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll(){
		List<Chamado> lista = chamadoService.listarChamados(); 
		// Converter a lista de chamados em lista de chamadosDTO
		List<ChamadoDTO> listaDTO = lista.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
		/* OBS: Criar uma lista de chamadosDTO, chamando a função stream e 
		 * Em seguida, mapear cada objeto dentro do stream, chamando o new ChamadoDTO e 
		 * passando o respectivo objeto. Finalmente, vou coletar tudo para a listaDTO.  
		 */
		return ResponseEntity.ok().body(listaDTO);
	}
	
	
    //*****************************
    //******* I N C L U I R *******
    //*****************************	
	// Endpoint para incluir um novo Chamado
	@PostMapping
	public ResponseEntity<ChamadoDTO> incluirChamado(@Valid @RequestBody ChamadoDTO objDTO) {
		Chamado newObj = chamadoService.incluirChamado(objDTO);
		// Quando criamos um novo objeto no BD, é uma boa prátoca retornar a URI desse novo objeto. Logo, temos:
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
    //*********************************
    //******* A T U A L I Z A R *******
    //*********************************	
	// Endpoint para atualizar os dados de um Chamado
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> atualizarChamado(@PathVariable Integer id, 
													   @Valid @RequestBody ChamadoDTO objDTO) {
		Chamado newObj = chamadoService.atualizarChamado(id, objDTO);
		
		return ResponseEntity.ok().body(new ChamadoDTO(newObj));
	}
	
	
}
