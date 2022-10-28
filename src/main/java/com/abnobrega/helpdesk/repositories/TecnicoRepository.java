package com.abnobrega.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abnobrega.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
