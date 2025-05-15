package com.hlca.hlcacursoservicio.Entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // Si necesitas constructor sin argumentos
@AllArgsConstructor // Si quieres un constructor con todos los argumentos
@Builder
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;
    private String nombre;
    private String horarios;
    private Integer capacidad;
    private Integer codigo;
    private String ciclo;
}
