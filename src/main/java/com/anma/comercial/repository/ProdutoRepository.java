package com.anma.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anma.comercial.model.Produto;
import com.anma.comercial.repository.produto.ProdutoRepositoryQuery;

public interface ProdutoRepository extends JpaRepository<Produto, Long>,ProdutoRepositoryQuery {


}
