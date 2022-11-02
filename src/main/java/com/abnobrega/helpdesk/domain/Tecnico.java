package com.abnobrega.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.abnobrega.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;	
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************		

	// TECNICO 1:n CHAMADO	
    @JsonIgnore // Informa para o parse OBJ <=> JSON que ignore esse atributo no JSON
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);	// RN001			
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);	// RN001			
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
