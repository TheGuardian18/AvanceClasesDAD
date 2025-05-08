package com.eja.prestamo.Service.ServiceImpl;


import com.eja.prestamo.Dto.LibroDto;
import com.eja.prestamo.Dto.UsuarioDto;
import com.eja.prestamo.Entity.Prestamo;
import com.eja.prestamo.Feign.LibroFeign;
import com.eja.prestamo.Feign.UsuarioFeign;
import com.eja.prestamo.Repository.PrestamoRepositorio;
import com.eja.prestamo.Service.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestamoServicioImpl implements PrestamoServicio {

    @Autowired
    private PrestamoRepositorio prestamoRepositorio;

    @Autowired
    private LibroFeign libroFeign;

    @Autowired
    private UsuarioFeign usuarioFeign;

    @Override
    public List<Prestamo> Listar() {
        return prestamoRepositorio.findAll().stream()
                .map(prestamo -> {
                    prestamo.setLibro(libroFeign.buscarLibro(prestamo.getLibroId()).getBody());
                    prestamo.setUsuario(usuarioFeign.buscarUsuario(prestamo.getUsuarioId()).getBody());
                    return prestamo;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Prestamo Buscar(Long id) {
        Optional<Prestamo> prestamoOptional = prestamoRepositorio.findById(id);
        if (prestamoOptional.isPresent()) {
            Prestamo prestamo = prestamoOptional.get();
            prestamo.setLibro(libroFeign.buscarLibro(prestamo.getLibroId()).getBody());
            prestamo.setUsuario(usuarioFeign.buscarUsuario(prestamo.getUsuarioId()).getBody());
            return prestamo;
        }
        return null;
    }

    @Override
    public Prestamo Guardar(Prestamo prestamo) {
        // Obtener usuario desde el microservicio
        UsuarioDto usuario = usuarioFeign.buscarUsuario(prestamo.getUsuarioId()).getBody();

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no existe.");
        }

        // Verificar si el usuario está activo
        if (!"activo".equalsIgnoreCase(usuario.getEstado())) {
            throw new IllegalStateException("El usuario no está activo y no puede registrar un préstamo.");
        }

        // Obtener libro y verificar copias disponibles
        LibroDto libro = libroFeign.buscarLibro(prestamo.getLibroId()).getBody();
        if (libro == null) {
            throw new IllegalArgumentException("El libro no existe.");
        }

        // Verificar si el libro tiene copias disponibles
        if (libro.getCopiasDisponibles() <= 0) {
            throw new IllegalStateException("No hay copias disponibles para el libro solicitado.");
        }

        // Verificar la cantidad de préstamos actuales para este libro
        long prestamosExistentes = prestamoRepositorio.countByLibroId(prestamo.getLibroId());
        if (prestamosExistentes >= 5) {
            throw new IllegalStateException("El libro ha alcanzado el límite de préstamos.");
        }

        // Si pasa todas las validaciones, guardar el préstamo
        Prestamo prestamoGuardado = prestamoRepositorio.save(prestamo);

        // Decrementar las copias disponibles después de crear el préstamo
        libro.setCopiasDisponibles(libro.getCopiasDisponibles() - 1);

        // Actualizar la cantidad de copias disponibles del libro
        libroFeign.actualizarLibro(libro.getId(), libro);

        prestamoGuardado.setLibro(libro);
        prestamoGuardado.setUsuario(usuario);

        return prestamoGuardado;
    }


    @Override
    public Prestamo Actualizar(Prestamo prestamo) {
        Prestamo actualizado = prestamoRepositorio.save(prestamo);
        actualizado.setLibro(libroFeign.buscarLibro(actualizado.getLibroId()).getBody());
        actualizado.setUsuario(usuarioFeign.buscarUsuario(actualizado.getUsuarioId()).getBody());
        return actualizado;
    }

    @Override
    public Prestamo Eliminar(Prestamo prestamo) {
        prestamoRepositorio.delete(prestamo);
        return prestamo;
    }

}