package com.anma.comercial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anma.comercial.model.Cliente;
import com.anma.comercial.model.Lancamento;
import com.anma.comercial.repository.ClienteRepository;
import com.anma.comercial.repository.LancamentoRepository;
import com.anma.comercial.service.exception.ClienteInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);

		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		if (!lancamento.getCliente().equals(lancamentoSalvo.getCliente())) {
			validarPessoa(lancamento);
		}

		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento) {
		Cliente cliente = null;
		if (lancamento.getCliente().getId() != null) {
			cliente = clienteRepository.getOne(lancamento.getCliente().getId());
		}

		if (cliente == null || cliente.isInativo()) {
			throw new ClienteInexistenteOuInativaException();
		}
	}

	private Lancamento buscarLancamentoExistente(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		if (lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}

}
