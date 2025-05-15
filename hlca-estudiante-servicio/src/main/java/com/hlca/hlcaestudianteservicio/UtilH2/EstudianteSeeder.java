package com.hlca.hlcaestudianteservicio.UtilH2;


import com.hlca.hlcaestudianteservicio.Entidad.Estudiante;
import com.hlca.hlcaestudianteservicio.Repositorio.EstudianteRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EstudianteSeeder implements CommandLineRunner {

    private final EstudianteRepositorio estudianteRepositorio;
    public EstudianteSeeder(EstudianteRepositorio estudianteRepositorio) {
        this.estudianteRepositorio = estudianteRepositorio;
    }
    @Override
    public void run(String... args){
        if (estudianteRepositorio.count() == 0) {
            // 1 para activos y 0 para inactivos
            Estudiante estudiante1 = new Estudiante(null, "pedrito" , "contabilidad", 1,3);
            Estudiante estudiante2 = new Estudiante(null, "elvis", "sistemas", 0,5);


            estudianteRepositorio.save(estudiante1);
            estudianteRepositorio.save(estudiante2);



            System.out.println("Datos de HLCA Estudiante insertados correctamente.");
        }else {
            System.out.println("Los HLCA Estudiantes ya existen, no se insertaron datos.");
        }
    }
}