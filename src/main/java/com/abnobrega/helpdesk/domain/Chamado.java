package com.abnobrega.helpdesk.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.abnobrega.helpdesk.domain.enums.PrioridadeChamado;
import com.abnobrega.helpdesk.domain.enums.StatusChamado;

public class Chamado {
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	private Integer id;
	private LocalDate dataAbertura = LocalDate.now(); //Recebe a data do sistema
	private LocalDate dataFechamento;
	private PrioridadeChamado prioridadeChamado;
	private StatusChamado statusChamado;
	private String titulo;
	private String observacoes;
	
	private Tecnico tecnico;
	private Cliente cliente;
	
	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public Chamado() {
		super();
	}

	public Chamado(Integer id, PrioridadeChamado prioridadeChamado, StatusChamado statusChamado, String titulo,
			String observacoes, Tecnico tecnico, Cliente cliente) {
		super();
		this.id = id;
		this.prioridadeChamado = prioridadeChamado;
		this.statusChamado = statusChamado;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.tecnico = tecnico;
		this.cliente = cliente;
		/* OBS: As datas de abertura e fechamento não são recebibas 
		 * pelo construtor, porque são geradas pelo sistema. 
		 * RN02: Quando cria um chamado o sistema automaticamente gera a data de abertura
		 * RN03: Quando fecha um chamado o sistema automaticamentegera a data de dechamento		 * 
		 */ 
	}

	//*************************
	//*******  MÉTODOS  *******
	//*************************		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PrioridadeChamado getPrioridadeChamado() {
		return prioridadeChamado;
	}

	public void setPrioridadeChamado(PrioridadeChamado prioridadeChamado) {
		this.prioridadeChamado = prioridadeChamado;
	}

	public StatusChamado getStatusChamado() {
		return statusChamado;
	}

	public void setStatusChamado(StatusChamado statusChamado) {
		this.statusChamado = statusChamado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
