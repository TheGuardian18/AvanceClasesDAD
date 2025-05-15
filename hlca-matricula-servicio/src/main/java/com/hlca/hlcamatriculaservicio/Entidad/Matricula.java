package com.hlca.hlcamatriculaservicio.Entidad;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.hlca.hlcamatriculaservicio.Dto.CursoDto;
import com.hlca.hlcamatriculaservicio.Dto.EstudianteDto;
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
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Long id;

    private Integer cicloMatricula;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.TRUE)
    private LocalDate fechaMatricula;

    private Long cursoId;
    @Transient
    private CursoDto cursoDto;

    private Long estudianteId;
    @Transient
    private EstudianteDto estudianteDto;

}
