package com.abnobrega.helpdesk.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.abnobrega.helpdesk.dtos.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************		
	private AuthenticationManager authenticationManager; // Interface de estratégia de autenticação
	
	private JWTUtil jwtUtil;

	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	} 
	
	//*************************
	//*******  MÉTODOS  *******
	//*************************	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			// request.getInputStream() =  método que lê o corpo da requição
			CredenciaisDTO credenciaisDTO = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(), credenciaisDTO.getSenha(), new ArrayList<>());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}
	
	// *** QUANDO A AUTENTICAÇÃO FOR BEM-SUCEDIDA ***
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//Fazer um parse do authResult (do tipo Authentication) para o tipo UserSS
		//authResult = Resultado da Autenticação e getPrincipal contém as informações da autenticação.
		String username = ((UserSS) authResult.getPrincipal()).getUsername();
		
		// Gerar o token para o usuário (username)
		String token = jwtUtil.gerarToken(username);
		
		// Adicionar as respostas no header da resposta, onde:
		// access-control-expose-header para eu poder pegar o token depois
		response.setHeader("access-control-expose-header", "Authorization");
		response.setHeader("Authorization", "Bearer " + token);		
		
		// Assim, em caso de sucesso eu retorno o token para o usuário. 
	}
	
	// *** QUANDO A AUTENTICAÇÃO NÃO FOR BEM-SUCEDIDA ***
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
		
		response.setStatus(401); 					// 401 Unauthorized
		response.setContentType("application/json");// O conteúdo da resposta é do tipo json.
		response.getWriter().append(json());
	}

	private CharSequence json() {
		long date = new Date().getTime();
		return "{"
				+ "\"timestamp\": " + date + ", " 
				+ "\"status\": 401, "
				+ "\"error\": \"Não autorizado\", "
				+ "\"message\": \"Email ou senha inválidos\", "
				+ "\"path\": \"/login\"}";
	}

}
