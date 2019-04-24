package com.kec.comercial.repository.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kec.comercial.model.Produto;
import com.kec.comercial.repository.filter.ProdutoFilter;

public interface ProdutoRepositoryQuery {
	public Page<Produto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);

}
