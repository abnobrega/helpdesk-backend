package com.abnobrega.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnobrega.helpdesk.domain.Chamado;
import com.abnobrega.helpdesk.domain.Cliente;
import com.abnobrega.helpdesk.domain.Tecnico;
import com.abnobrega.helpdesk.domain.enums.PrioridadeChamado;
import com.abnobrega.helpdesk.domain.enums.StatusChamado;
import com.abnobrega.helpdesk.dtos.ChamadoDTO;
import com.abnobrega.helpdesk.repositories.ChamadoRepository;
import com.abnobrega.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	
    //*********************************
    //******* C O N S U L T A R *******
    //*********************************	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = chamadoRepository.findById(id);
		/*SE (achar o chamado pelo id) ENTÃO 
			Retorna o obj 'chamado'. 
		  SENÃO 
		    Lança uma exceção aqui.
		  FIM-SE
		*/
		return obj.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id:" + id)); 
	}

	public List<Chamado> listarChamados() {
		return chamadoRepository.findAll();
	}
	
    //*****************************
    //******* I N C L U I R *******
    //*****************************		
	public Chamado incluirChamado(ChamadoDTO objDTO) {
		return chamadoRepository.save(gerarNovoChamado(objDTO));
	}
	
	private	Chamado gerarNovoChamado(ChamadoDTO objDTO) {
		// Verificar se existe o id do Técnico que veio no parâmetro objDTO. 
		Tecnico tecnico = tecnicoService.findById(objDTO.getIdTecnico());

		// Verificar se existe o id do Cliente que veio no parâmetro objDTO.		
		Cliente cliente = clienteService.findById(objDTO.getIdCliente());
		
		Chamado chamado = new Chamado(); // Usa o construtor sem parâmetro para criar um chamado
		
		if (objDTO.getId() != null) {
			// Deseja atualizar um chamado já existente no BD
			chamado.setId(objDTO.getId());
		} 
		
		// Atribui os valores do chamado.
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setPrioridadeChamado(PrioridadeChamado.toEnum(objDTO.getIdPrioridadeChamado()));
		chamado.setStatusChamado(StatusChamado.toEnum(objDTO.getIdStatusChamado()));
		chamado.setTitulo(objDTO.getTitulo());
		chamado.setObservacoes(objDTO.getObservacoes());
		return chamado; 
	}

}
