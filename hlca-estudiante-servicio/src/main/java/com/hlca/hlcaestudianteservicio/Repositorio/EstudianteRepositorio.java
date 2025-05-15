package com.hlca.hlcaestudianteservicio.Repositorio;

import com.hlca.hlcaestudianteservicio.Entidad.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
}
