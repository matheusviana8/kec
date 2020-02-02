package com.anma.comercial.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anma.comercial.dto.LancamentoEstatisticaCliente;
import com.anma.comercial.model.Cliente;
import com.anma.comercial.model.Lancamento;
import com.anma.comercial.repository.ClienteRepository;
import com.anma.comercial.repository.LancamentoRepository;
import com.anma.comercial.repository.filter.LancamentoFilter;
import com.anma.comercial.service.exception.ClienteInexistenteOuInativaException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class LancamentoService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public byte[] relatorioPorData(LancamentoFilter lancamentoFilter) throws Exception {
		List<Lancamento> dados = lancamentoRepository.porData(lancamentoFilter);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", lancamentoFilter.getDataVencimentoDe());
		parametros.put("DT_FIM",    lancamentoFilter.getDataVencimentoAte());
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/lancamentos-por-data.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	public byte[] relatorioPorCliente(LocalDate inicio, LocalDate fim) throws Exception {
		List<LancamentoEstatisticaCliente> dados = lancamentoRepository.porCliente(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/lancamentos-por-pessoa.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);

		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		if (!lancamento.getCliente().equals(lancamentoSalvo.getCliente())) {
			validarPessoa(lancamento);
		}

		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");

		return lancamentoRepository.save(lancamentoSalvo);
	}

	private void validarPessoa(Lancamento lancamento) {
		Cliente cliente = null;
		if (lancamento.getCliente().getId() != null) {
			cliente = clienteRepository.getOne(lancamento.getCliente().getId());
		}

		if (cliente == null || cliente.isInativo()) {
			throw new ClienteInexistenteOuInativaException();
		}
	}

	private Lancamento buscarLancamentoExistente(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		if (lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}

}
