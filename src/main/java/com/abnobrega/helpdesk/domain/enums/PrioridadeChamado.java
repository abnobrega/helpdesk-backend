package com.abnobrega.helpdesk.domain.enums;

// OBS: O enum é um array!
public enum PrioridadeChamado {
	
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	private Integer codigo;
	private String descrição;
	
	//**************************
	//******* CONSTRUTOR *******
	//**************************		
	private PrioridadeChamado(Integer codigo, String descrição) {
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
	public static PrioridadeChamado toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(PrioridadeChamado x : PrioridadeChamado.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		//Lançar exceção
		throw new IllegalArgumentException("Prioridade do chamado inválida.");
	}
	
}
