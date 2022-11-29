package com.abnobrega.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void instanciaDB() {
		// Instancia um técnico
		Tecnico tec1 = new Tecnico(null, "Alexandre Bonturi Nóbrega", "001.323.097-26", "abonturi@gmail.com", bCryptPasswordEncoder.encode("123"));
		tec1.setPerfil(Perfil.ADMIN);
		
		Tecnico tec2 = new Tecnico(null, "Ricardo Stallman", "623.572.010-61", "rsatllman@gmail.com", bCryptPasswordEncoder.encode("123"));
		tec2.setPerfil(Perfil.TECNICO);		
		Tecnico tec3 = new Tecnico(null, "Cláudia Shanon", "290.951.710-19", "cshanon@gmail.com", bCryptPasswordEncoder.encode("123"));
		tec3.setPerfil(Perfil.TECNICO);			
		Tecnico tec4 = new Tecnico(null, "Mônica Veloso", "715.946.450-86", "mveloso@gmail.com", bCryptPasswordEncoder.encode("123"));
		tec4.setPerfil(Perfil.TECNICO);			
		Tecnico tec5 = new Tecnico(null, "Linus Tovalds", "012.958.250-66", "ltovalds@gmail.com", bCryptPasswordEncoder.encode("123"));		
		tec5.setPerfil(Perfil.TECNICO);
		Tecnico tec6 = new Tecnico(null, "Euclides de Alexandria", "633.233.440-14", "euclides@gmail.com", bCryptPasswordEncoder.encode("123"));		
		tec6.setPerfil(Perfil.TECNICO);			

		Cliente cli1 = new Cliente(null, "Albert Einstein", "561.428.420-66", "aeinstein@gmail.com", bCryptPasswordEncoder.encode("123"));
		cli1.setPerfil(Perfil.CLIENTE);
		Cliente cli2 = new Cliente(null, "Marie Curie", "029.270.800-97", "mcurie@gmail.com", bCryptPasswordEncoder.encode("123"));
		cli2.setPerfil(Perfil.CLIENTE);	
		Cliente cli3 = new Cliente(null, "Charles Darwin", "086.200.030-03", "cdarwin@gmail.com", bCryptPasswordEncoder.encode("123"));
		cli3.setPerfil(Perfil.CLIENTE);
		Cliente cli4 = new Cliente(null, "Tom Jobim", "732.178.090-25", "tjobim@gmail.com", bCryptPasswordEncoder.encode("123"));
		cli4.setPerfil(Perfil.CLIENTE);			
		Cliente cli5 = new Cliente(null, "Ivan Lins", "747.052.940-33", "ilins@gmail.com", bCryptPasswordEncoder.encode("123"));
		cli5.setPerfil(Perfil.CLIENTE);			

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
