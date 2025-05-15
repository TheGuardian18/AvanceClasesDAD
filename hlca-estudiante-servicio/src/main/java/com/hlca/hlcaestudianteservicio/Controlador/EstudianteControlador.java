package com.hlca.hlcaestudianteservicio.Controlador;

import com.hlca.hlcaestudianteservicio.Entidad.Estudiante;
import com.hlca.hlcaestudianteservicio.Servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteControlador {

    @Autowired
    private EstudianteServicio KR;

    // Obtener todas las Estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiante() {
        List<Estudiante> apoderados = KR.Listar();
        return new ResponseEntity<>(apoderados, HttpStatus.OK);
    }

    // Obtener una Estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarEstudiante(@PathVariable Long id) {
        Optional<Estudiante> apoderado = KR.Buscar(id);
        return apoderado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva Estudiante
    @PostMapping
    public ResponseEntity<Estudiante> guardarEstudiante(@RequestBody Estudiante apoderado) {
        Estudiante nuevoApoderado = KR.Guardar(apoderado);
        return new ResponseEntity<>(nuevoApoderado, HttpStatus.CREATED);
    }


    // Actualizar o modificar una Estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> modificarEstudiante(@PathVariable Long id, @RequestBody Estudiante apoderado) {
        Estudiante apoderadoModificado = KR.Modificar(id, apoderado);
        return apoderadoModificado != null ? new ResponseEntity<>(apoderadoModificado, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    // Eliminar una Estudiante por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Estudiante> eliminarEstudiante(@PathVariable Long id) {
        KR.Eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


