package com.kec.comercial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kec.comercial.model.Cliente;
import com.kec.comercial.model.Pedido;
import com.kec.comercial.repository.ClienteRepository;
import com.kec.comercial.repository.PedidoRepository;
import com.kec.comercial.service.exception.ClienteInexistenteOuInativaException;

@Service
public class PedidoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private PedidoRepository pedidoRepository;

	public Pedido salvar(Pedido pedido) {
		Cliente cliente = clienteRepository.findOne(pedido.getCliente().getId());
		if (cliente == null || cliente.isInativo()) {
			throw new ClienteInexistenteOuInativaException();
		}
		
		pedido.getItensPedido().forEach(i -> i.setPedido(pedido));
		//getContatos().forEach(c -> c.setPessoa(pessoa));
		
		return pedidoRepository.save(pedido);
	}
	
	public Pedido atualizar(Long id, Pedido pedido) {
		Pedido pedidoSalvo = buscarPedido(id);
		pedido.getItensPedido().forEach(i -> i.setPedido(pedido));
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "id");
		
		return pedidoRepository.save(pedidoSalvo);
	}
	
	private Pedido buscarPedido(Long id) {
		Pedido pedidoSalvo = pedidoRepository.findOne(id);
		
		if (pedidoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pedidoSalvo;
	}

}
