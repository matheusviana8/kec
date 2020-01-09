package com.anma.comercial.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anma.comercial.event.RecursoCriadoEvent;
import com.anma.comercial.model.Vendedor;
import com.anma.comercial.repository.VendedorRepository;

@CrossOrigin
@RestController
@RequestMapping("/vendedores")
public class VendedorController {
	
	@Autowired
	private VendedorRepository vendedores;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	@ResponseBody
	public List<Vendedor> listar(){
		return vendedores.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vendedor> buscar(@PathVariable long id) {
		Vendedor vendedor = vendedores.findOne(id);
		return vendedor != null ? ResponseEntity.ok(vendedor) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Vendedor> adicionar(@Valid @RequestBody Vendedor vendedor, HttpServletResponse response) {
		Vendedor vendedorSalvo = vendedores.save(vendedor);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, vendedorSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(vendedorSalvo);
	}
	
	
}
