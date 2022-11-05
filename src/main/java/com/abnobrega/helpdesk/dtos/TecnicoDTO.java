package com.abnobrega.helpdesk.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.abnobrega.helpdesk.domain.Tecnico;
import com.abnobrega.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * Essa classe vai retornar as informações do método GET: findById { "id": 1,
	 * "nome": "Alexandre B", "cpf": "00132309726", "email": "abonturi@gmail.com",
	 * "senha": "123", "dataCriacao": "01/11/2022", "perfis": [ "ADMIN", "CLIENTE" ]
	 * }
	 */

	// *************************
	// ******* ATRIBUTOS *******
	// *************************
	protected Integer id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();
	protected Set<Integer> perfis = new HashSet<>();

	// ****************************
	// ******* CONSTRUTORES *******
	// ****************************
	public TecnicoDTO() {
		super();
		setPerfil(Perfil.CLIENTE);		
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.dataCriacao = obj.getDataCriacao();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		/* O atributo perfis é do tipo Integer e o obj.getPerfis retorna uma lista de perfis
		 * Logo, como são tipos incompatíveis, fazemos um stream e mapeamos cada x, 
		 * onde para cada perfil x vamos chamar o método getCodigo e adicionamos na minha lista. 
		 * Pra isso, dou um collect, coletando tudo Collector e fazendo a conversão para Set (lista) 
		 */		
		setPerfil(Perfil.CLIENTE);
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void setPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
	
}
