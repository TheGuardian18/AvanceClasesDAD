package com.eja.prestamo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDto {
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private Integer copiasDisponibles;
    private String categoria;
    private String isbn;
}
