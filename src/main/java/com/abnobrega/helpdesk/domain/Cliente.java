package com.abnobrega.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.abnobrega.helpdesk.domain.enums.Perfil;

@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;	
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************		
	@OneToMany(mappedBy = "cliente")	
	private List<Chamado> chamados = new ArrayList<>();

	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public Cliente() {
		super();
		addPerfis(Perfil.CLIENTE);	// RN001
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfis(Perfil.CLIENTE);	// RN001		
	}

	//*************************
	//*******  MÉTODOS  *******
	//*************************	
	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}	

}
