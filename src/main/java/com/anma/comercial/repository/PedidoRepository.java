package com.anma.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anma.comercial.model.Pedido;
import com.anma.comercial.repository.pedido.PedidoRepositoryQuery;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryQuery {
	

}
