package com.anma.comercial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.anma.comercial.model.Categoria;
import com.anma.comercial.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categorias;

	public Categoria atualizar(Long id, Categoria categoria) {
		Categoria categoriaSalvo = categorias.findOne(id);
		
		if (categoriaSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(categoria, categoriaSalvo, "id");
		
		return categorias.save(categoriaSalvo);
	}
}
