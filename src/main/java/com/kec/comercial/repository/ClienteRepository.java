package com.kec.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kec.comercial.model.Cliente;
import com.kec.comercial.repository.cliente.ClienteRepositoryQuery;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQuery {


}
