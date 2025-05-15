package com.hlca.hlcaestudianteservicio.Servicio.Implementos;

import com.hlca.hlcaestudianteservicio.Entidad.Estudiante;
import com.hlca.hlcaestudianteservicio.Repositorio.EstudianteRepositorio;
import com.hlca.hlcaestudianteservicio.Servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicioImplemento implements EstudianteServicio {
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    @Override
    public List<Estudiante> Listar() {
        return estudianteRepositorio.findAll();
    }
    @Override
    public Optional<Estudiante> Buscar(Long id) {
        return estudianteRepositorio.findById(id);
    }
    @Override
    public Estudiante Guardar(Estudiante estudiante) {
        return estudianteRepositorio.save(estudiante);
    }
    @Override
    public Estudiante Modificar(Long id, Estudiante estudiante) {
        estudiante.setId(id);
        return estudianteRepositorio.save(estudiante);
    }
    @Override
    public void Eliminar(Long id) {
        estudianteRepositorio.deleteById(id);
    }
}
