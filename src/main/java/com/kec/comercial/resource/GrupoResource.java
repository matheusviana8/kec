package com.kec.comercial.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kec.comercial.event.RecursoCriadoEvent;
import com.kec.comercial.model.Grupo;
import com.kec.comercial.repository.GrupoRepository;
import com.kec.comercial.repository.filter.GrupoFilter;
import com.kec.comercial.service.GrupoService;

@CrossOrigin
@RestController
@RequestMapping("/grupos")
public class GrupoResource {
	
	@Autowired
	private GrupoRepository grupos;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_GRUPO') and #oauth2.hasScope('read')")
	public Page<Grupo> pesquisar(GrupoFilter grupoFilter, Pageable pageable) {
		return grupos.filtrar(grupoFilter,pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')")
	public ResponseEntity<Grupo> criar(@Valid @RequestBody Grupo grupo, HttpServletResponse response) {
		Grupo grupoSalvo = grupos.save(grupo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, grupoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoSalvo);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_GRUPO') and #oauth2.hasScope('read')")
	public ResponseEntity<Grupo> buscarPeloId(@PathVariable Long id) {
		Grupo grupo = grupos.findOne(id);
		return grupo != null ? ResponseEntity.ok(grupo) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')")
	public ResponseEntity<Grupo> atualizar(@PathVariable Long id, @Valid @RequestBody Grupo grupo){
		Grupo grupoSalvo = grupoService.atualizar(id, grupo);
		return ResponseEntity.ok(grupoSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_GRUPO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		grupos.delete(id);		
	}
	

}
