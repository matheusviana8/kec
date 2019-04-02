package com.kec.comercial.repository.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kec.comercial.model.Pedido;
import com.kec.comercial.repository.filter.PedidoFilter;
import com.kec.comercial.repository.projection.ResumoPedido;

public interface PedidoRepositoryQuery {
	
	public Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable);
	public Page<ResumoPedido> resumir(PedidoFilter pedidoFilter, Pageable pageable);
}
