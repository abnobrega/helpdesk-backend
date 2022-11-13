package com.abnobrega.helpdesk.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abnobrega.helpdesk.domain.Chamado;
import com.abnobrega.helpdesk.dtos.ChamadoDTO;
import com.abnobrega.helpdesk.service.ChamadoService;

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
	
	
	
}
