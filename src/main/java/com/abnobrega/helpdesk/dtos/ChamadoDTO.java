package com.abnobrega.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.abnobrega.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ChamadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now(); //Recebe a data do sistema
	@JsonFormat(pattern = "dd/MM/yyyy")	
	private LocalDate dataFechamento;
	@NotNull(message = "O campo PRIORIDADE é obrigatório")
	private Integer idPrioridadeChamado;
	@NotNull(message = "O campo STATUS é obrigatório")
	private Integer idStatusChamado;
	@NotNull(message = "O campo TÍTULO é obrigatório")	
	private String titulo;
	@NotNull(message = "O campo OBSERVAÇÕES é obrigatório")		
	private String observacoes;
	@NotNull(message = "O campo TÉCNICO é obrigatório")		
	private Integer idTecnico;
	private String nomeTecnico;	
	@NotNull(message = "O campo CLIENTE é obrigatório")		
	private Integer idCliente;
	private String nomeCliente;
	
	// OBS: O nome do cliente e o nome do técnico é gerado pelo back-end.
	
	// ****************************
	// ******* CONSTRUTORES *******
	// ****************************	
	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.idPrioridadeChamado = obj.getPrioridadeChamado().getCodigo();
		this.idStatusChamado = obj.getStatusChamado().getCodigo();
		this.titulo = obj.getTitulo();
		this.observacoes = obj.getObservacoes();		
		this.idTecnico = obj.getTecnico().getId();
		this.nomeTecnico = obj.getTecnico().getNome();
		this.idCliente = obj.getCliente().getId();
		this.nomeCliente = obj.getCliente().getNome();
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

	public Integer getIdPrioridadeChamado() {
		return idPrioridadeChamado;
	}

	public void setIdPrioridadeChamado(Integer idPrioridadeChamado) {
		this.idPrioridadeChamado = idPrioridadeChamado;
	}

	public Integer getIdStatusChamado() {
		return idStatusChamado;
	}

	public void setIdStatusChamado(Integer idStatusChamado) {
		this.idStatusChamado = idStatusChamado;
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

	public Integer getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(Integer idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

}
