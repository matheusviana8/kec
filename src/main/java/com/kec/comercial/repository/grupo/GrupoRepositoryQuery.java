package com.kec.comercial.repository.grupo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kec.comercial.model.Grupo;
import com.kec.comercial.repository.filter.GrupoFilter;

public interface GrupoRepositoryQuery {
	
	public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable);

}
