package com.hlca.hlcacursoservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HlcaCursoServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(HlcaCursoServicioApplication.class, args);
    }

}
