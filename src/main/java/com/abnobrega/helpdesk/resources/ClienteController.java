package com.abnobrega.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abnobrega.helpdesk.domain.Cliente;
import com.abnobrega.helpdesk.dtos.ClienteDTO;
import com.abnobrega.helpdesk.services.ClienteService;

//********************************************
//************** API de CLIENTE **************
//********************************************
//Endpoint incial para acessar os serviços/recursos do cliente.
@RestController
@RequestMapping(value = "/api/clientes") 
public class ClienteController {
    // Essa classe é um controlador REST de cliente, que vai receber as requisições HTTP e 
	// vai se comunicar dentro da arquitetura REST. => Requisição http://localhost/api/clientes

    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************
	@Autowired
	private ClienteService clienteService;
	
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	
    //*********************************
    //******* C O N S U L T A R *******
    //*********************************
	// Endpoints para consultar Cliente pelo id	
	@GetMapping(value = "/{id}")
	// localhost/api/tecnicos/id	
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		Cliente obj = clienteService.findById(id);
		// Retornar um ResponseEntity, onde ok(Fiz a requisição) e no body dessa resposta 
		// retorno um objeto clienteDTO, usando o construtor que passa como parâmetro um cliente.		
		return ResponseEntity.ok().body(new ClienteDTO(obj));			
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> lista = clienteService.listarClientes();
		// Converter a lista de clientes em lista de clientesDTO		
		List<ClienteDTO> listaDTO = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		/* OBS: Criar uma lista de clientesDTO, chamando a função stream e 
		 * Em seguida, mapear cada objeto dentro do stream, chamando o new ClienteDTO e 
		 * passando o respectivo objeto. Finalmente, vou coletar tudo para a listaDTO.  
		 */		
		return ResponseEntity.ok().body(listaDTO); // No corpo da resposta retorno uma lista.
	}

    //*****************************
    //******* E X C L U I R *******
    //*****************************
	// Endpoint para excluir um Cliente	
	@DeleteMapping(value = "/{id}") // Endpoint que recebe o id na URL
	public ResponseEntity<ClienteDTO> excluirCliente(@PathVariable Integer id) {
		clienteService.excluirCliente(id);
		return ResponseEntity.noContent().build();
	}
		
    //*****************************
    //******* I N C L U I R *******
    //*****************************	
	// Endpoint para incluir um novo Cliente
	@PostMapping
	public ResponseEntity<ClienteDTO> incluirCliente(@Valid @RequestBody ClienteDTO objDTO) {
		Cliente newObj = clienteService.inluirCliente(objDTO);
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
	// Endpoint para atualizar os dados de um Cliente	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Integer id,
													   @Valid @RequestBody ClienteDTO objDto){
		Cliente objAtualizado = clienteService.atualizarCliente(id, objDto);

		// No corpo da resposta crio um novo tecnicoDTO passando o objAtualizado como parâmetro.
		return ResponseEntity.ok().body(new ClienteDTO(objAtualizado));
	}

}
