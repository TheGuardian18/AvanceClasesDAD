package com.hlca.hlcacursoservicio.Controlador;

import com.hlca.hlcacursoservicio.Entidad.Curso;
import com.hlca.hlcacursoservicio.Servicio.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoControlador {

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCurso() {
        List<Curso> cursos = cursoServicio.Listar();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCurso(@PathVariable Long id) {
        Optional<Curso> curso = cursoServicio.Buscar(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Curso> guardarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoServicio.Guardar(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> modificarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Curso cursoModificado = cursoServicio.Modificar(id, curso);
        return cursoModificado != null ? new ResponseEntity<>(cursoModificado, HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Curso> eliminarCurso(@PathVariable Long id) {
        cursoServicio.Eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
