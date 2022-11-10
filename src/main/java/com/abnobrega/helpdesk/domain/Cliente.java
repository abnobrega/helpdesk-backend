package com.abnobrega.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.abnobrega.helpdesk.domain.enums.Perfil;
import com.abnobrega.helpdesk.dtos.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente extends Pessoa {
	private static final long serialVersionUID = 1L;	
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	

	// CLIENTE 1:n CHAMADO 	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")	
	private List<Chamado> chamados = new ArrayList<>();

	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public Cliente() {
		super();
		setPerfil(Perfil.CLIENTE);	// RN001
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		setPerfil(Perfil.CLIENTE);	// RN001		
	}
	
	public Cliente(ClienteDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.dataCriacao = obj.getDataCriacao();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());	
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
