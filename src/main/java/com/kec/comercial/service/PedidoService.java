package com.kec.comercial.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kec.comercial.model.Pedido;
import com.kec.comercial.model.StatusPedido;
import com.kec.comercial.repository.ClienteRepository;
import com.kec.comercial.repository.PedidoRepository;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PedidoService {
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoService produtoService;

	public Pedido salvar(Pedido pedido) {
		/*
		Cliente cliente = clienteRepository.findOne(pedido.getCliente().getId());
		if (cliente == null || cliente.isInativo()) {
			throw new ClienteInexistenteOuInativaException();
		}*/
		//TODO: RETIRAR COMENTARIO
		//setando pedido nos itens pedidos
		pedido.getItensPedido().forEach(i -> i.setPedido(pedido));
		
		//getContatos().forEach(c -> c.setPessoa(pessoa));
		
		return pedidoRepository.save(pedido);
	}
	
	public Pedido atualizar(Long id, Pedido pedido) {
		Pedido pedidoSalvo = buscarPedido(id);
		
		//setando pedido nos itens pedidos/seriais
		pedidoSalvo.getItensPedido().clear();
		pedidoSalvo.getItensPedido().addAll(pedido.getItensPedido());
		pedidoSalvo.getItensPedido().forEach(i -> i.setPedido(pedidoSalvo));
		
		pedidoSalvo.getItensSerial().clear();
		pedidoSalvo.getItensSerial().addAll(pedido.getItensSerial());
		pedidoSalvo.getItensSerial().forEach(i -> i.setPedido(pedidoSalvo));
		
		//ATUALIZAR SALDO DOS PRODUTOS SOMENTE QUANDO O PEDIDO FOR EMITIDO
		if (pedido.getStatus() == StatusPedido.EMITIDO && pedidoSalvo.getStatus() != StatusPedido.EMITIDO) {
			produtoService.atualizarSaldo(pedido.getItensPedido(),pedido.getTipo());
		}
		
		BeanUtils.copyProperties(pedido, pedidoSalvo, "id","itensPedido","itensSerial");
		//aqui fica a regra de atualização do pedido
		return pedidoRepository.save(pedidoSalvo);
	}
	
	private Pedido buscarPedido(Long id) {
		Pedido pedidoSalvo = pedidoRepository.findOne(id);
		
		if (pedidoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pedidoSalvo;
	}
	
	public byte[] imprimirPedido(Pedido pedido) throws Exception {
		//List<Pedido> dados = new ArrayList<Pedido>();
		//dados.add(pedido);
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/pedido.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(pedido.getItensPedido()));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
