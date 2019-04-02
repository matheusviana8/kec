package com.kec.comercial.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kec.comercial.model.Grupo;
import com.kec.comercial.repository.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupos;

	public Grupo atualizar(Long id, Grupo grupo) {
		Grupo grupoSalvo = grupos.findOne(id);
		
		if (grupoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(grupo, grupoSalvo, "id");
		
		return grupos.save(grupoSalvo);
	}
}
