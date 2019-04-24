package com.kec.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kec.comercial.model.Produto;
import com.kec.comercial.repository.produto.ProdutoRepositoryQuery;

public interface ProdutoRepository extends JpaRepository<Produto, Long>,ProdutoRepositoryQuery {


}
