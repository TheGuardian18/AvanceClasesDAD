package com.hlca.hlcamatriculaservicio.Servicio;

import com.hlca.hlcamatriculaservicio.Entidad.Matricula;

import java.util.List;

public interface MatriculaServicio {


    List<Matricula> Listar();
    Matricula Buscar(Long id);
    Matricula Guardar(Matricula matricula);
    Matricula Actualizar(Matricula matricula);


}
