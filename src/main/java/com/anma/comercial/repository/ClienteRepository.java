package com.anma.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anma.comercial.model.Cliente;
import com.anma.comercial.repository.cliente.ClienteRepositoryQuery;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {


}
