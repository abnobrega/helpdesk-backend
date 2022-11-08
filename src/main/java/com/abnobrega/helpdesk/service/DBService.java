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
		Tecnico tec1 = new Tecnico(null, "Alexandre Bonturi Nóbrega", "001.323.097-26", "abonturi@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "Ricardo Stallman", "623.572.010-61", "rsatllman@gmail.com", "123");
		tec2.addPerfil(Perfil.TECNICO);		
		Tecnico tec3 = new Tecnico(null, "Cláudia Shanon", "290.951.710-19", "cshanon@gmail.com", "123");
		tec3.addPerfil(Perfil.TECNICO);			
		Tecnico tec4 = new Tecnico(null, "Mônica Veloso", "715.946.450-86", "mveloso@gmail.com", "123");
		tec4.addPerfil(Perfil.TECNICO);			
		Tecnico tec5 = new Tecnico(null, "Linus Tovalds", "012.958.250-66", "ltovalds@gmail.com", "123");		
		tec5.addPerfil(Perfil.TECNICO);
		Tecnico tec6 = new Tecnico(null, "Bebe legal", "633.233.440-14", "blegal@gmail.com", "123");		
		tec6.addPerfil(Perfil.TECNICO);			

		Cliente cli1 = new Cliente(null, "Albert Einstein", "561.428.420-66", "aeinstein@gmail.com", "1234");
		cli1.addPerfil(Perfil.CLIENTE);
		Cliente cli2 = new Cliente(null, "Marie Curie", "029.270.800-97", "mcurie@gmail.com", "1234");
		cli2.addPerfil(Perfil.CLIENTE);	
		Cliente cli3 = new Cliente(null, "Charles Darwin", "086.200.030-03", "cdarwin@gmail.com", "1234");
		cli3.addPerfil(Perfil.CLIENTE);
		Cliente cli4 = new Cliente(null, "Tom Jobim", "732.178.090-25", "tjobim@gmail.com", "1234");
		cli4.addPerfil(Perfil.CLIENTE);			
		Cliente cli5 = new Cliente(null, "Ivan Lins", "747.052.940-33", "ilins@gmail.com", "1234");
		cli5.addPerfil(Perfil.CLIENTE);			

		Chamado cha1 = new Chamado(null, PrioridadeChamado.MEDIA, StatusChamado.ANDAMENTO, "Chamado 01",
				"Teste chamado 1", tec1, cli1);
		Chamado cha2 = new Chamado(null, PrioridadeChamado.ALTA, StatusChamado.ABERTO, "Chamado 02",
				"Teste chamado 2", tec1, cli2);		
		Chamado cha3 = new Chamado(null, PrioridadeChamado.BAIXA, StatusChamado.ENCERRADO, "Chamado 03",
				"Teste chamado 3", tec2, cli3);
		Chamado cha4 = new Chamado(null, PrioridadeChamado.ALTA, StatusChamado.ABERTO, "Chamado 04",
				"Teste chamado 4", tec3, cli4);
		Chamado cha5 = new Chamado(null, PrioridadeChamado.MEDIA, StatusChamado.ANDAMENTO, "Chamado 05",
				"Teste chamado 5", tec2, cli1);
		Chamado cha6 = new Chamado(null, PrioridadeChamado.BAIXA, StatusChamado.ENCERRADO, "Chamado 06",
				"Teste chamado 6", tec4, cli5);
		Chamado cha7 = new Chamado(null, PrioridadeChamado.ALTA, StatusChamado.ANDAMENTO, "Chamado 07",
				"Teste chamado 7", tec5, cli5);		

		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, tec6));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		chamadoRepository.saveAll(Arrays.asList(cha1, cha2, cha3, cha4, cha5, cha6, cha7));
	}
}
