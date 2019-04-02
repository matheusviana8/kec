package com.kec.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kec.comercial.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
