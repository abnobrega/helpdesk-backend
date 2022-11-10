package com.abnobrega.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnobrega.helpdesk.domain.Chamado;
import com.abnobrega.helpdesk.repositories.ChamadoRepository;
import com.abnobrega.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {
	
    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
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

}
