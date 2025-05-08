package com.eja.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EjaUsuarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjaUsuarioServiceApplication.class, args);
    }

}
