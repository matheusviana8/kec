package com.anma.comercial.repository.grupo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anma.comercial.model.Grupo;
import com.anma.comercial.repository.filter.GrupoFilter;

public interface GrupoRepositoryQuery {
	
	public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable);

}
