package com.anma.comercial.repository.pedido;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anma.comercial.dto.PedidoEstatisticaDia;
import com.anma.comercial.model.Pedido;
import com.anma.comercial.repository.filter.PedidoFilter;
import com.anma.comercial.repository.projection.ResumoPedido;

public interface PedidoRepositoryQuery {
	
	public List<PedidoEstatisticaDia> porDia(LocalDate mesReferencia);
	
	public Page<Pedido> filtrar(PedidoFilter pedidoFilter, Pageable pageable);
	public Page<ResumoPedido> resumir(PedidoFilter pedidoFilter, Pageable pageable);
}
