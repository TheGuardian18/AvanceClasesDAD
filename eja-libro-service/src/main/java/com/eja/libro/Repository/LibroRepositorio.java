package com.eja.libro.Repository;

import com.eja.libro.Entity.Libro;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    boolean existsByIsbn(String isbn);
}
