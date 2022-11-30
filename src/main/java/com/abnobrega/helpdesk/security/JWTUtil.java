package com.abnobrega.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

	public boolean tokenValido(String token) {
        // Obtem as informaçoes do token contidas no CLAIMS (reivindicações do token)
		Claims claims = getClaim(token);
		if (claims != null) {
			// Obtém o usuário 
			String username = claims.getSubject();
			// Obtém a data de expiração do token.			
			Date expirationDate = claims.getExpiration();
			// Obtém a data corrente em milisegundos	
			Date now = new Date(System.currentTimeMillis());
			
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaim(String token) {
		try {
			return Jwts
					.parser()								// Método para decodificar o token
					.setSigningKey(chaveSecreta.getBytes())	// Informa a chave secreta do token
					.parseClaimsJws(token)					// Passo o token que será decodificado
					.getBody();								// Método para retornar o corpo (informações) do token
		} catch(Exception e) {
			return null;
		}
	}
	
	public String getUsername(String token) {
		Claims claims = getClaim(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
}
