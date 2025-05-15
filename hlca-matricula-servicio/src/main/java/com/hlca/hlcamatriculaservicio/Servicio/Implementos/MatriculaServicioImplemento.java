package com.hlca.hlcamatriculaservicio.Servicio.Implementos;

import com.hlca.hlcamatriculaservicio.Entidad.Matricula;
import com.hlca.hlcamatriculaservicio.Feign.CursoFeign;
import com.hlca.hlcamatriculaservicio.Feign.EstudianteFeign;
import com.hlca.hlcamatriculaservicio.Repositorio.MatriculaRepositorio;
import com.hlca.hlcamatriculaservicio.Servicio.MatriculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServicioImplemento implements MatriculaServicio {
    @Autowired
    private CursoFeign cursoFeign;
    @Autowired
    private EstudianteFeign estudianteFeign;
    @Autowired
    private MatriculaRepositorio matriculaRepositorio;

    @Override
    public List<Matricula> Listar(){
        List<Matricula> matriculas = matriculaRepositorio.findAll();
        for (Matricula matricula : matriculas) {
            try {
                matricula.setCursoDto(cursoFeign.buscarCurso(matricula.getCursoId()).getBody());
            } catch (Exception e) {
                // Log the error or handle it as needed.
                System.err.println("Error al obtener el curso con ID: " + matricula.getCursoId() + " - " + e.getMessage());
                matricula.setCursoDto(null); // Or some default value
            }
            try {
                matricula.setEstudianteDto(estudianteFeign.buscarEstudiante(matricula.getEstudianteId()).getBody());
            } catch (Exception e) {
                // Log the error or handle it as needed.
                System.err.println("Error al obtener el estudiante con ID: " + matricula.getEstudianteId() + " - " + e.getMessage());
                matricula.setEstudianteDto(null); // Or some default value
            }
        }
        return matriculas;
    }

    @Override
    public Matricula Buscar(Long id){
        Optional<Matricula> optionalMatricula = matriculaRepositorio.findById(id);
        if(optionalMatricula.isPresent()) {
            Matricula matricula = optionalMatricula.get();
            matricula.setCursoDto(cursoFeign.buscarCurso(matricula.getCursoId()).getBody());
            matricula.setEstudianteDto(estudianteFeign.buscarEstudiante(matricula.getEstudianteId()).getBody());
            return matricula;
        } else {
            // Puedes lanzar una excepción personalizada o retornar null según tu lógica de negocio
            return null;
        }
    }

    @Override
    public Matricula Guardar(Matricula matricula){
        Matricula nuevaMatricula = matriculaRepositorio.save(matricula);
        // Enriquecer la nueva matrícula con los DTOs antes de retornar
        nuevaMatricula.setCursoDto(cursoFeign.buscarCurso(nuevaMatricula.getCursoId()).getBody());
        nuevaMatricula.setEstudianteDto(estudianteFeign.buscarEstudiante(nuevaMatricula.getEstudianteId()).getBody());
        return nuevaMatricula;
    }

    @Override
    public Matricula Actualizar(Matricula matricula){
        if (matriculaRepositorio.existsById(matricula.getId())) {
            Matricula matriculaActualizada = matriculaRepositorio.save(matricula);
            try {
                matriculaActualizada.setCursoDto(cursoFeign.buscarCurso(matriculaActualizada.getCursoId()).getBody());
            } catch (Exception e) {
                System.err.println("Error al obtener el curso con ID: " + matriculaActualizada.getCursoId() + " - " + e.getMessage());
                matriculaActualizada.setCursoDto(null);
            }
            try {
                matriculaActualizada.setEstudianteDto(estudianteFeign.buscarEstudiante(matriculaActualizada.getEstudianteId()).getBody());
            } catch (Exception e) {
                System.err.println("Error al obtener el estudiante con ID: " + matriculaActualizada.getEstudianteId() + " - " + e.getMessage());
                matriculaActualizada.setEstudianteDto(null);
            }
            return matriculaActualizada;
        } else {
            return null; // Indicate that the entity was not found
        }
    }


}
