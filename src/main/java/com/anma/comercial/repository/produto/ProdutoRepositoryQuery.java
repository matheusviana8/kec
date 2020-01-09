package com.anma.comercial.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anma.comercial.model.Produto;
import com.anma.comercial.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {
	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);

}
