package com.abnobrega.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.abnobrega.helpdesk.domain.enums.PrioridadeChamado;
import com.abnobrega.helpdesk.domain.enums.StatusChamado;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Chamado implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now(); //Recebe a data do sistema
	
	@JsonFormat(pattern = "dd/MM/yyyy")	
	private LocalDate dataFechamento;
	
	private PrioridadeChamado prioridadeChamado;
	private StatusChamado statusChamado;
	private String titulo;
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;

	@ManyToOne
	@JoinColumn(name = "cliente_id")	
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
	
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
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
