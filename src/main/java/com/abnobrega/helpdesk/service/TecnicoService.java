package com.abnobrega.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnobrega.helpdesk.domain.Tecnico;
import com.abnobrega.helpdesk.repositories.TecnicoRepository;
import com.abnobrega.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
    //*************************************************
    //**************  A T R I B U T O S  **************
    //*************************************************
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
    //*************************************************
    //************** M  É  T  O  D  O  S **************
    //*************************************************
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
	}
}
