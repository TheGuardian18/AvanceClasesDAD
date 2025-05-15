package com.hlca.hlcamatriculaservicio.Repositorio;

import com.hlca.hlcamatriculaservicio.Entidad.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepositorio extends JpaRepository<Matricula, Long> {
}
