package com.abnobrega.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnobrega.helpdesk.domain.Cliente;
import com.abnobrega.helpdesk.domain.Pessoa;
import com.abnobrega.helpdesk.dtos.ClienteDTO;
import com.abnobrega.helpdesk.repositories.ClienteRepository;
import com.abnobrega.helpdesk.repositories.PessoaRepository;
import com.abnobrega.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.abnobrega.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************
	@Autowired
	private ClienteRepository clienteRepository;	// Injeta um ClienteRepository
	
	@Autowired
	private PessoaRepository pessoaRepository;		// Injeta uma pessoaRepository
	
	
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	
    //*********************************
    //******* C O N S U L T A R *******
    //*********************************
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
	}

	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}
	
    //*****************************
    //******* E X C L U I R *******
    //*****************************
	public void excluirCliente(Integer id) {
		Cliente obj = findById(id);
		
		// Se o Cliente possui qualquer chamado para ele, não pderá ser excluído. RN006
		if (obj.getChamados().size() > 0){
			// lançar uma exceção aqui		
			throw new DataIntegrityViolationException("Cliente possui chamado e não pode ser excluído.");
		}
		
		clienteRepository.deleteById(id);
	}
	
    //*****************************
    //******* I N C L U I R *******
    //*****************************		
	public Cliente inluirCliente(ClienteDTO objDTO) {

		// Validar o CPF e o EMAIL
		validaPorCpfEEmail(objDTO);
		
		objDTO.setId(null); // Por questão de segurança
		
		// Converter ClienteDTO em Técnico (entidade) 
		Cliente newObj = new Cliente(objDTO); // Criar um Cliente à partir de um ClienteDTO.
		
		return clienteRepository.save(newObj);
	}

	// Método que recebe um ClienteDTO como parâmetro e valida por CPF e EMAIL
	private void validaPorCpfEEmail(ClienteDTO objDTO) {

		// VALIDA O CPF
		Optional<Pessoa> obj1 = pessoaRepository.findByCpf(objDTO.getCpf());
		// obj.isPresent() significa que ele existe
		if ( obj1.isPresent() && (obj1.get().getId() != objDTO.getId()) ) {
			// lançar uma exceção aqui		
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		// VALIDA O EMAIL		
		Optional<Pessoa> obj2 = pessoaRepository.findByEmail(objDTO.getEmail());
		if ( obj2.isPresent() && (obj2.get().getId() != objDTO.getId()) ) {
			// lançar uma exceção aqui		
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
	}

    //*********************************
    //******* A T U A L I Z A R *******
    //*********************************	
	public Cliente atualizarCliente(Integer id, @Valid ClienteDTO objDto) {
		// Por segurança, atribuir o id recebido ao id do objDTO
		objDto.setId(id);
		
		// Verifica se o Cliente existe e, caso não exista, será lançada uma exceção
		Cliente objAtualizado = findById(id); 
		
		// Valida por CPF e EMAIL
		validaPorCpfEEmail(objDto);
		
		// Se técnico existe e CPF e EMAIL estão corretos, então 
		// Cria um Cliente atualizado à partir de um ClienteDTO.
		objAtualizado = new Cliente(objDto); 
		
		return clienteRepository.save(objAtualizado);
	}

}
