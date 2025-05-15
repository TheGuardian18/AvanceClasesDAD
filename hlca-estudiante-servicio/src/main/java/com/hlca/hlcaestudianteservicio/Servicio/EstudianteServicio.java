package com.hlca.hlcaestudianteservicio.Servicio;

import com.hlca.hlcaestudianteservicio.Entidad.Estudiante;

import java.util.List;
import java.util.Optional;

public interface EstudianteServicio {
    List<Estudiante> Listar();
    Optional<Estudiante> Buscar(Long id);
    Estudiante Guardar(Estudiante estudiante);
    Estudiante Modificar(Long id, Estudiante estudiante);
    void Eliminar(Long id);

}
