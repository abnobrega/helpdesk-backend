package com.abnobrega.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abnobrega.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
