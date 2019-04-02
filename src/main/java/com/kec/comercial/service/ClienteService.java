package com.kec.comercial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kec.comercial.model.Cliente;
import com.kec.comercial.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalvo = buscarCliente(id);
		
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		
		return clienteRepository.save(clienteSalvo);
	}
	
	private Cliente buscarCliente(Long id) {
		Cliente clienteSalvo = clienteRepository.findOne(id);
		
		if (clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clienteSalvo;
	}


}
