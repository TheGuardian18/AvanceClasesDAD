package com.hlca.hlcacursoservicio.UtilH2;

import com.hlca.hlcacursoservicio.Entidad.Curso;
import com.hlca.hlcacursoservicio.Repositorio.CursoRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CursoSeeder implements CommandLineRunner {

    private final CursoRepositorio cursoRepositorio;

    public CursoSeeder(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    @Override
    public void run(String... args){
        if (cursoRepositorio.count() == 0) {
            // Crear algunos cursos de ejemplo
            Curso curso1 = new Curso(null, "Matemáticas I", "Lunes 8-10 AM", 30, 101, "2025-I");
            Curso curso2 = new Curso(null, "Introducción a la Programación", "Martes 2-4 PM", 25, 102, "2025-I");
            Curso curso3 = new Curso(null, "Física Básica", "Miércoles 10-12 AM", 35, 103, "2025-I");
            Curso curso4 = new Curso(null, "Química General", "Jueves 4-6 PM", 28, 104, "2025-I");
            Curso curso5 = new Curso(null, "Matemáticas II", "Lunes 8-10 AM", 30, 201, "2025-II");
            Curso curso6 = new Curso(null, "Programación Orientada a Objetos", "Martes 2-4 PM", 25, 202, "2025-II");

            // Guardar los cursos en la base de datos H2
            cursoRepositorio.save(curso1);
            cursoRepositorio.save(curso2);
            cursoRepositorio.save(curso3);
            cursoRepositorio.save(curso4);
            cursoRepositorio.save(curso5);
            cursoRepositorio.save(curso6);

            System.out.println("Datos de HLCA Curso insertados correctamente en H2.");
        } else {
            System.out.println("Los datos de HLCA Curso ya existen en H2, no se insertaron datos.");
        }
    }
}