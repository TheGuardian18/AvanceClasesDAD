package com.hlca.hlcamatriculaservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HlcaMatriculaServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(HlcaMatriculaServicioApplication.class, args);
    }

}
