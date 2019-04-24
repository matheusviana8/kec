package com.kec.comercial.repository.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kec.comercial.model.Cliente;
import com.kec.comercial.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}
