package com.eja.prestamo.Entity;

import com.eja.prestamo.Dto.LibroDto;
import com.eja.prestamo.Dto.UsuarioDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor // Si necesitas constructor sin argumentos
@AllArgsConstructor // Si quieres un constructor con todos los argumentos
@Builder
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    private Long id;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaPrestamo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaDevolucion;

    private Long usuarioId;

    @Transient
    private UsuarioDto usuario;

    private Long libroId;

    @Transient
    private LibroDto libro;

}
