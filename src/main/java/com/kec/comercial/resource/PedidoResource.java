package com.kec.comercial.resource;

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
import org.springframework.http.HttpStatus;
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

import com.kec.comercial.event.RecursoCriadoEvent;
import com.kec.comercial.exceptionhandler.KecExceptionHandler.Erro;
import com.kec.comercial.model.Pedido;
import com.kec.comercial.repository.PedidoRepository;
import com.kec.comercial.repository.filter.PedidoFilter;
import com.kec.comercial.repository.projection.ResumoPedido;
import com.kec.comercial.service.PedidoService;
import com.kec.comercial.service.exception.ClienteInexistenteOuInativaException;

@CrossOrigin
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;

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
	public ResponseEntity<Pedido> adicionar(@Valid @RequestBody Pedido Pedido, HttpServletResponse response) {
		Pedido pedidoSalvo = pedidoService.salvar(Pedido);
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
		pedidoRepository.delete(id);		
	}
	
	
	@ExceptionHandler({ ClienteInexistenteOuInativaException.class })
	public ResponseEntity<Object> handleClienteInexistenteOuInativaException(ClienteInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("cliente.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
