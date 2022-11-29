package com.abnobrega.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;	
		
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	
    //*********************************
    //******* C O N S U L T A R *******
    //*********************************
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
	}

	public List<Tecnico> ListarTecnicos() {
		return tecnicoRepository.findAll();
	}
	
    //*****************************
    //******* E X C L U I R *******
    //*****************************
	public void excluirTecnico(Integer id) {
		Tecnico obj = findById(id);
		
		// Se o tecnico possui qualquer chamado para ele, não pderá ser excluído. RN006
		if (obj.getChamados().size() > 0){
			// lançar uma exceção aqui		
			throw new DataIntegrityViolationException("Tecnico possui chamado e não pode ser excluído.");
		}
		
		tecnicoRepository.deleteById(id);
	}
	
    //*****************************
    //******* I N C L U I R *******
    //*****************************		
	public Tecnico inluirTecnico(TecnicoDTO objDTO) {

		// Validar o CPF e o EMAIL
		validaPorCpfEEmail(objDTO);
		
		objDTO.setId(null); // Por questão de segurança
		objDTO.setSenha(bCryptPasswordEncoder.encode(objDTO.getSenha())); // Encriptografa a senha do USUA		
		
		// Converter TecnicoDTO em Técnico (entidade) 
		Tecnico newObj = new Tecnico(objDTO); // Criar um tecnico à partir de um tcnicoDTO.
		
		return tecnicoRepository.save(newObj);
	}

	// Método que recebe um TecnicoDTO como parâmetro e valida por CPF e EMAIL
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

    //*********************************
    //******* A T U A L I Z A R *******
    //*********************************	
	public Tecnico atualizarTecnico(Integer id, @Valid TecnicoDTO objDto) {
		// Por segurança, atribuir o id recebido ao id do objDTO
		objDto.setId(id);
		
		// Verifica se o tecnico existe e, caso não exista, será lançada uma exceção
		Tecnico objAtualizado = findById(id); 
		
		// Valida por CPF e EMAIL
		validaPorCpfEEmail(objDto);
		
		// Se técnico existe e CPF e EMAIL estão corretos, então 
		// Cria um tecnico atualizado à partir de um tecnicoDTO.
		objAtualizado = new Tecnico(objDto); 
		
		// Salva o Técnico Atualizado na BD
		return tecnicoRepository.save(objAtualizado);
	}

}
