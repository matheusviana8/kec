package com.anma.comercial.repository.lancamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anma.comercial.dto.LancamentoEstatisticaCategoria;
import com.anma.comercial.dto.LancamentoEstatisticaCliente;
import com.anma.comercial.dto.LancamentoEstatisticaDia;
import com.anma.comercial.model.Lancamento;
import com.anma.comercial.repository.filter.LancamentoFilter;
import com.anma.comercial.repository.projection.ResumoLancamento;


public interface LancamentoRepositoryQuery {
	
	public List<LancamentoEstatisticaCliente> porCliente(LocalDate inicio, LocalDate fim);
	public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);
	public List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia);

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);	
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
}
