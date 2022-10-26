package com.abnobrega.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************		
	private List<Chamado> chamados = new ArrayList<>();

	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
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
