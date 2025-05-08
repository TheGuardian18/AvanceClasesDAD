package com.eja.libro.Controller;
import com.eja.libro.Entity.Libro;
import com.eja.libro.Service.LibroServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @GetMapping
    public ResponseEntity<List<Libro>> listar() {
        return ResponseEntity.ok(libroServicio.Listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscar(@PathVariable Long id) {
        Optional<Libro> libro = libroServicio.Buscar(id);
        return libro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> guardar(@RequestBody Libro libro) {
        Libro nuevo = libroServicio.Guardar(libro);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> modificar(@PathVariable Long id, @RequestBody Libro libro) {
        Libro actualizado = libroServicio.Modificar(id, libro);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroServicio.Eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // NUEVO: Obtener varios libros por lista de IDs
    @PostMapping("/lista")
    public ResponseEntity<List<Libro>> buscarVarios(@RequestBody List<Long> ids) {
        List<Libro> libros = libroServicio.BuscarVarios(ids);
        return ResponseEntity.ok(libros);
    }
}
