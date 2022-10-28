package com.abnobrega.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abnobrega.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
