package com.hlca.hlcamatriculaservicio.UtilH2;

import com.hlca.hlcamatriculaservicio.Entidad.Matricula;
import com.hlca.hlcamatriculaservicio.Repositorio.MatriculaRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MatriculaSeeder implements CommandLineRunner {

    private final MatriculaRepositorio matriculaRepositorio;

    public MatriculaSeeder(MatriculaRepositorio matriculaRepositorio) {
        this.matriculaRepositorio = matriculaRepositorio;
    }

    @Override
    public void run(String... args) throws Exception {
        if (matriculaRepositorio.count() == 0) {
            // Crear algunas matrículas de ejemplo
            Matricula matricula1 = new Matricula(null, 20251, LocalDate.of(2025, 3, 15), 1L, null, 1L, null);
            Matricula matricula2 = new Matricula(null, 20251, LocalDate.of(2025, 3, 16), 2L, null, 2L, null);
            Matricula matricula3 = new Matricula(null, 20252, LocalDate.of(2025, 8, 10), 3L, null, 1L, null);
            //Matricula matricula4 = new Matricula(null, 20251, LocalDate.now(), 103L, null, 3L, null);

            // Guardar las matrículas en la base de datos H2
            matriculaRepositorio.save(matricula1);
            matriculaRepositorio.save(matricula2);
            matriculaRepositorio.save(matricula3);
            //matriculaRepositorio.save(matricula4);
            //El tercer parámetro es LocalDate.now(). Esto significa que la fecha de matrícula para este registro en particular se establecerá a la fecha actual del sistema en el momento en que se ejecuta el MatriculaSeeder.

            System.out.println("Datos de HLCA Matrícula insertados correctamente en H2.");
        } else {
            System.out.println("Los datos de HLCA Matrícula ya existen en H2, no se insertaron datos.");
        }
    }
}