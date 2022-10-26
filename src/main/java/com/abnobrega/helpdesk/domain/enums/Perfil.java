package com.abnobrega.helpdesk.domain.enums;

// OBS: O enum é um array!
public enum Perfil {
	
	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"),	TECNICO(2, "ROLE_TECNICO");
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	private Integer codigo;
	private String descrição;
	
	//**************************
	//******* CONSTRUTOR *******
	//**************************		
	private Perfil(Integer codigo, String descrição) {
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
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		//Lançar exceção
		throw new IllegalArgumentException("Perfil inválido.");
	}
	
}
