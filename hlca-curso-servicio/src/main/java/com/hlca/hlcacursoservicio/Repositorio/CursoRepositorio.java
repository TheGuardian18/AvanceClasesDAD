package com.hlca.hlcacursoservicio.Repositorio;

import com.hlca.hlcacursoservicio.Entidad.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CursoRepositorio extends JpaRepository<Curso, Long> {
}
