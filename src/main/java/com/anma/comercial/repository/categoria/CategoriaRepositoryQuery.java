package com.anma.comercial.repository.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anma.comercial.model.Categoria;
import com.anma.comercial.repository.filter.CategoriaFilter;

public interface CategoriaRepositoryQuery {
	
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable);

}
