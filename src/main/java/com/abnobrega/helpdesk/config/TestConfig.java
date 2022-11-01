package com.abnobrega.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.abnobrega.helpdesk.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	// Injeta uma instância de DBService aqui!
	@Autowired
	private DBService dbService;

	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}

}
