package com.anma.comercial.resource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anma.comercial.dto.PedidoEstatisticaDia;
import com.anma.comercial.event.RecursoCriadoEvent;
import com.anma.comercial.exceptionhandler.KecExceptionHandler.Erro;
import com.anma.comercial.model.ItemPedido;
import com.anma.comercial.model.Pedido;
import com.anma.comercial.model.StatusPedido;
import com.anma.comercial.repository.PedidoRepository;
import com.anma.comercial.repository.filter.PedidoFilter;
import com.anma.comercial.repository.projection.ResumoPedido;
import com.anma.comercial.service.PedidoService;
import com.anma.comercial.service.ProdutoService;
import com.anma.comercial.service.exception.ClienteInexistenteOuInativaException;

@CrossOrigin
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/imprimir/{id}")
	public ResponseEntity<byte[]> imprimirPedido(@PathVariable long id) throws Exception {
		Pedido pedido = pedidoRepository.findOne(id);
		System.out.println(pedido.getCliente());
		byte[] relatorio = pedidoService.imprimirPedido(pedido);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@GetMapping("/estatisticas/por-dia")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PEDIDO') and #oauth2.hasScope('read')")
	public List<PedidoEstatisticaDia> porDia() {
		return this.pedidoRepository.porDia(LocalDate.now());
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PEDIDO') and #oauth2.hasScope('read')")
	public Page<Pedido> pesquisar(PedidoFilter pedidoFilter, Pageable pageable) {
		return pedidoRepository.filtrar(pedidoFilter,pageable);
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PEDIDO') and #oauth2.hasScope('read')")
	public Page<ResumoPedido> resumir(PedidoFilter pedidoFilter, Pageable pageable) {
		return pedidoRepository.resumir(pedidoFilter,pageable);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PEDIDO') and #oauth2.hasScope('read')")
	public ResponseEntity<Pedido> buscarPeloId(@PathVariable long id) {
		Pedido pedido = pedidoRepository.findOne(id);
		return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PEDIDO') and #oauth2.hasScope('write')")
	public ResponseEntity<Pedido> adicionar(@Valid @RequestBody Pedido pedido, HttpServletResponse response) {
		pedido.setDataCriacao(LocalDate.now());
		
		BigDecimal soma = BigDecimal.ZERO;
		/*for(int i = 0;i<pedido.getItensPedido().size();i++) {
			
		}*/
		//produtoService.atualizarSaldo(pedido.getItensPedido());
		
		
		for (ItemPedido item : pedido.getItensPedido()) {
			soma = soma.add(item.getValorTotal());
		}
		pedido.setValorTotal(soma);
		//atualizar saldos de produto
		
		Pedido pedidoSalvo = pedidoService.salvar(pedido);
		
		if (pedido.getStatus() == StatusPedido.EMITIDO) {
			produtoService.atualizarSaldo(pedido.getItensPedido(),pedido.getTipo());
		}
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pedidoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
		
	}
	

	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PEDIDO') and #oauth2.hasScope('write')")
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido pedido){
		Pedido pedidoSalvo = pedidoService.atualizar(id, pedido);
		return ResponseEntity.ok(pedidoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PEDIDO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		Pedido pedido = pedidoRepository.findOne(id);
		String tipo = "";
		
		if (pedido.getTipo().equals("E")) {
			tipo = "S";
		}else {
			tipo = "E";
		}
		
		pedidoRepository.delete(id);
		produtoService.atualizarSaldo(pedido.getItensPedido(),tipo);
				
	}
	
	
	@ExceptionHandler({ ClienteInexistenteOuInativaException.class })
	public ResponseEntity<Object> handleClienteInexistenteOuInativaException(ClienteInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("cliente.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
