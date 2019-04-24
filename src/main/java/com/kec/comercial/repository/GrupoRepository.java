package com.kec.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kec.comercial.model.Grupo;
import com.kec.comercial.repository.grupo.GrupoRepositoryQuery;

public interface GrupoRepository extends JpaRepository<Grupo, Long>, GrupoRepositoryQuery {

}
