package com.eja.prestamo.Controller;


import com.eja.prestamo.Entity.Prestamo;
import com.eja.prestamo.Service.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/prestamo")
public class PrestamoControlador {

    @Autowired
    public PrestamoServicio prestamoServicio;

    @GetMapping
    public ResponseEntity<List<Prestamo>> listar() {
        return new ResponseEntity<>(prestamoServicio.Listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscar(@PathVariable Long id) {
        Prestamo prestamo = prestamoServicio.Buscar(id);
        if (prestamo != null) {
            return new ResponseEntity<>(prestamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Prestamo> guardar(@RequestBody Prestamo prestamo) {
        Prestamo nuevoPrestamo = prestamoServicio.Guardar(prestamo);
        return new ResponseEntity<>(nuevoPrestamo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        Prestamo prestamoExistente = prestamoServicio.Buscar(id);
        if (prestamoExistente != null) {
            prestamo.setId(id); // Asegura que el ID se mantenga
            Prestamo prestamoActualizado = prestamoServicio.Actualizar(prestamo);
            return new ResponseEntity<>(prestamoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Prestamo prestamoAEliminar = new Prestamo();
        prestamoAEliminar.setId(id);
        prestamoServicio.Eliminar(prestamoAEliminar);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}