package com.anma.comercial.repository.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anma.comercial.model.Cliente;
import com.anma.comercial.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
