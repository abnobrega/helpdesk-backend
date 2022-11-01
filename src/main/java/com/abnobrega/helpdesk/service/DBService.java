package com.abnobrega.helpdesk.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abnobrega.helpdesk.domain.Chamado;
import com.abnobrega.helpdesk.domain.Cliente;
import com.abnobrega.helpdesk.domain.Tecnico;
import com.abnobrega.helpdesk.domain.enums.Perfil;
import com.abnobrega.helpdesk.domain.enums.PrioridadeChamado;
import com.abnobrega.helpdesk.domain.enums.StatusChamado;
import com.abnobrega.helpdesk.repositories.ChamadoRepository;
import com.abnobrega.helpdesk.repositories.ClienteRepository;
import com.abnobrega.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	// Injetando dependências dessas minhas interfaces, nesse trecho do meu código.
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		// Instancia um técnico
		Tecnico tecnico1 = new Tecnico(null, "Alexandre B", "00132309726", "abonturi@gmail.com", "123");
		tecnico1.addPerfil(Perfil.ADMIN);

		Cliente cliente1 = new Cliente(null, "Linus Tovalds", "93490390016", "tovalds@gmail.com", "1234");
		cliente1.addPerfil(Perfil.CLIENTE);

		Chamado chamado1 = new Chamado(null, PrioridadeChamado.MEDIA, StatusChamado.ANDAMENTO, "Chamado 01",
				"Primeio chamado", tecnico1, cliente1);

		tecnicoRepository.saveAll(Arrays.asList(tecnico1));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
	}
}
