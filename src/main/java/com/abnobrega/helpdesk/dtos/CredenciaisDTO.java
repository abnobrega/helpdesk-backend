package com.abnobrega.helpdesk.dtos;

// Esta classe vai servir para a conversão do USUA que virá na requisição de login.
// Como essa classe não vai ser trafegada em rede, não precisa do Serializable. 
public class CredenciaisDTO {
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************			
	private String email;
	private String senha;
	
	//*************************
	//*******  MÉTODOS  *******
	//*************************		
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
	
}
