package com.abnobrega.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abnobrega.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
