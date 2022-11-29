package com.abnobrega.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************		
	@Value("${jwt.expiration}")  // Injetando o período de expiraçao do token
	private Long expiracaoToken;
	
	@Value("${jwt.secret}")
	private String chaveSecreta;  // Injetando a chave secreta
	
	//*************************
	//*******  MÉTODOS  *******
	//*************************		
	public String gerarToken(String email) {
		//Criar um token para o username (email)
		return Jwts
				.builder()
				.setSubject(email)		// Identificação para saber QUEM é o USUA que fez a requisição
				.setExpiration(new Date(System.currentTimeMillis() + expiracaoToken)) // Data atual + tempo de expiração
				.signWith(SignatureAlgorithm.HS512, chaveSecreta.getBytes())
				.compact();
	}
	
}
