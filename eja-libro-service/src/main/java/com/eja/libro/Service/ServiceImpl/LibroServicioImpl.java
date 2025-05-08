package com.eja.libro.Service.ServiceImpl;

import com.eja.libro.Entity.Libro;
import com.eja.libro.Repository.LibroRepositorio;
import com.eja.libro.Service.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServicioImpl implements LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Override
    public List<Libro> Listar() {
        return libroRepositorio.findAll();
    }

    @Override
    public Optional<Libro> Buscar(Long id) {
        return libroRepositorio.findById(id);
    }

    @Override
    public Libro Guardar(Libro libro) {
        if (libroRepositorio.existsByIsbn(libro.getIsbn())) {
            throw new RuntimeException("El ISBN ya está registrado para otro libro.");
        }
        return libroRepositorio.save(libro);
    }

    @Override
    public Libro Modificar(Long id, Libro libro) {
        libro.setId(id);
        return libroRepositorio.save(libro);
    }

    @Override
    public void Eliminar(Long id) {
        libroRepositorio.deleteById(id);
    }

    @Override
    public List<Libro> BuscarVarios(List<Long> ids) {
        return libroRepositorio.findAllById(ids);
    }
}
