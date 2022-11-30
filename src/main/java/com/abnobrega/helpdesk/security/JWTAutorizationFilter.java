package com.abnobrega.helpdesk.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAutorizationFilter extends BasicAuthenticationFilter{

	//*************************
	//******* ATRIBUTOS *******
	//*************************	
	private JWTUtil jwtUtil; 	
	
	private UserDetailsService userDetailsService;
	
	//****************************
	//******* CONSTRUTORES *******
	//****************************	
	public JWTAutorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService ) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	//*************************
	//*******  MÉTODOS  *******
	//*************************	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        // OBTER AS INFORMAÇÕES DA REQUISIÇÃO

		// 1.1) Interceptar o Header da requisição e capturar o valor do atributo autorização.
		String header = request.getHeader("Authorization");

        // 1.2) Verificar se o Header é do tipo Bearer
		if (header != null && header.startsWith("Bearer ")) {
			
			// 1.3) Pegar o valor do token à partir da sétima posição dentro da variável header
			String token = null;
			token = header.substring(7);
			UsernamePasswordAuthenticationToken authToken = getAuthentication(token);
			
            // 1.4) VERIFICAR SE O TOKEN ESTÁ VÁLIDO
			if (authToken != null) { 
				// Token válido: pegar o contexto da aplicação e setar o authentication passando o token válido. 
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		//  Verificar se o token é válido.
		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			
			//Carrega os detalhes do usuário pelo username; 
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
		}
		return null;
	}
	
}
