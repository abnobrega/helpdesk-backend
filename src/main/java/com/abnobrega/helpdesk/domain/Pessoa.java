package com.abnobrega.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.abnobrega.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public abstract class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	protected Integer id;
	protected String nome;
	
	@CPF // Amotation para verificar se o CPF é válido
	@Column(name = "cpf", unique = true)
	protected String cpf;
	
	@Column(unique = true)
	protected String email;
	protected String senha;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();	
	
	// Lista de perfis, onde: HashSet evita ponteiros nulos e Set evita perfis duplicados
	@ElementCollection(fetch = FetchType.EAGER) // Ao consultar o usua, a lista de perfis vem junto
	@CollectionTable(name = "PERFIS")
	protected Set<Integer> perfis = new HashSet<>();

	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public Pessoa() {
		super();
		// RN001: Todo usuário criado terá pelo menos o perfil CLIENTE.
		setPerfil(Perfil.CLIENTE);
	}

	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		setPerfil(Perfil.CLIENTE); 	// RN001		
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
		/* Nesse método get, retorno uma lista de perfil. Mas, a minha lista é 
		 * do tipo integer. Logo, os tipos primitivos não estão compativeis.
		 * Para resolver isso, damos um stream na lista de perfis e 
		 * vamos mapear cada perfil x, onde chamamos o método toEnum do Perfil,
		 * onde passamos um código e verificamos se esse código existe. 
		 * SE (codigo existe) ENTÃO  
		 *    Retornamos o perfil fazendo a conversão para uma lista do tipo Set.
		 * FIM-SE
		 */
		// Conforme abaixo:
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	// Eu recebo um perfil como parâmetro e quero retornar uma lista do tipo integer.
	public void setPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
