package com.anma.comercial.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anma.comercial.model.Lancamento;
import com.anma.comercial.repository.filter.LancamentoFilter;
import com.anma.comercial.repository.projection.ResumoLancamento;


public interface LancamentoRepositoryQuery {
	

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}
