package com.hlca.hlcamatriculaservicio.Controlador;

import com.hlca.hlcamatriculaservicio.Entidad.Matricula;
import com.hlca.hlcamatriculaservicio.Servicio.MatriculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaControlador {
    @Autowired
    public MatriculaServicio matriculaServicio;

    @GetMapping
    public ResponseEntity<List<Matricula>> Listar(){
        return new ResponseEntity<>(matriculaServicio.Listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> Buscar(@PathVariable Long id){
        Matricula matricula = matriculaServicio.Buscar(id);
        if (matricula != null) {
            return new ResponseEntity<>(matricula, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Matricula> Guardar(@RequestBody Matricula matricula){
        Matricula nuevaMatricula = matriculaServicio.Guardar(matricula);
        return new ResponseEntity<>(nuevaMatricula, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> Actualizar(@PathVariable Long id, @RequestBody Matricula matricula){
        matricula.setId(id); // Aseguramos que el ID sea el correcto
        Matricula matriculaActualizada = matriculaServicio.Actualizar(matricula);
        if (matriculaActualizada != null) {
            return new ResponseEntity<>(matriculaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
