package com.abnobrega.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnobrega.helpdesk.domain.Pessoa;
import com.abnobrega.helpdesk.domain.Tecnico;
import com.abnobrega.helpdesk.dtos.TecnicoDTO;
import com.abnobrega.helpdesk.repositories.PessoaRepository;
import com.abnobrega.helpdesk.repositories.TecnicoRepository;
import com.abnobrega.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.abnobrega.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************
	@Autowired
	private TecnicoRepository tecnicoRepository;	// Injeta um tecnicoRepository
	
	@Autowired
	private PessoaRepository pessoaRepository;		// Injeta uma pessoaRepository
	
	
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
	}

	public List<Tecnico> ListarTecnicos() {
		return tecnicoRepository.findAll();
	}

	public Tecnico inluirTecnico(TecnicoDTO objDTO) {

		// Validar o CPF e o EMAIL
		validaPorCpfEEmail(objDTO);
		
		objDTO.setId(null); // Por questão de segurança
		// Converter TecnicoDTO em Técnico (entidade) 
		Tecnico newObj = new Tecnico(objDTO); // Criar um tecnico à partir de um DTO.
		return tecnicoRepository.save(newObj);
	}

	// Método que recebe um TecnicoDTO como parâmetro
	private void validaPorCpfEEmail(TecnicoDTO objDTO) {

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

}
