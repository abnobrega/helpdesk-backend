package com.abnobrega.helpdesk.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.abnobrega.helpdesk.security.JWTAutorizationFilter;
import com.abnobrega.helpdesk.security.JWTUtil;
import com.abnobrega.helpdesk.security.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};  
	
	//*************************
	//******* ATRIBUTOS *******
	//*************************		
	@Autowired
	private Environment environment; // Injeta uma instância de Environment aqui.
	@Autowired
	private JWTUtil jwtUtil; // Injeta uma instância de JWTUtil aqui.
	@Autowired
	private UserDetailsService userDetailsService; // Injeta uma instância de UserDetailsService aqui
	
	//*************************
	//*******  MÉTODOS  *******
	//*************************			
	// Este método abaixo é onde adiciono minhas defesas contra vulnerabilidades 
	// nos meus endpoints, adiciono conigurações de filtros, etc.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Verifica se ele tem o perfil de teste
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		// Adiciono a minha configuração de cors neste meu método configure. Assim,
		// informo que tenho uma configuração de cors e quero que ela seja aplicada, conforme 
		// o @bean abaixo
		http.cors().and().csrf().disable();
		http.addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAutorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		
		// Para garantir que minha aplicação é STATLESS (Sem armazenamento de sessão de usua) 
		// e asseguro que a sessão de usuário não vai ser criada.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	// Meus endpoins estão bloqueados. Portanto, preciso liberar os acessos das requisições 
	// para o meu back-end. Para tanto, devo configurar o cross-origin para receber requisições 
	// de multiplas fontes: FRONT-END, API, SEFVIÇOS, ETC. (Liberar meus endpoints)
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	// Esse Bean é quem vai criptografar e descriptografar as senhas dos usuários
    // Configuração do Bean que vai representar o filtro JWT e vai interceptar as requisições.
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // BCryptPasswordEncoder é um pacote do Spring Framework bem avançado de autenticação
		return new BCryptPasswordEncoder();
	}
	
}
