package com.abnobrega.helpdesk.domain.enums;

// OBS: O enum é um array!
public enum StatusChamado {
	
	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	private Integer codigo;
	private String descrição;
	
	//**************************
	//******* CONSTRUTOR *******
	//**************************		
	private StatusChamado(Integer codigo, String descrição) {
		this.codigo = codigo;
		this.descrição = descrição;
	}

	//*************************
	//*******  MÉTODOS  *******
	//*************************	
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescrição() {
		return descrição;
	}

	// Como não vou criar uma instância de Perfil, temos:
	public static StatusChamado toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatusChamado x : StatusChamado.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		//Lançar exceção
		throw new IllegalArgumentException("Status do chamado inválido.");
	}
	
}
