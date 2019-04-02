package com.kec.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kec.comercial.model.Pedido;
import com.kec.comercial.repository.pedido.PedidoRepositoryQuery;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, PedidoRepositoryQuery {
	

}
