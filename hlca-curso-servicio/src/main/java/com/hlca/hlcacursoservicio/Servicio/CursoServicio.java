package com.hlca.hlcacursoservicio.Servicio;

import com.hlca.hlcacursoservicio.Entidad.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoServicio {

    List<Curso> Listar();
    Optional<Curso> Buscar(Long id);
    Curso Guardar(Curso curso);
    Curso Modificar(Long id, Curso curso);
    void Eliminar(Long id);

}
