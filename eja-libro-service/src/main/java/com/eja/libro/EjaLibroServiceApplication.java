package com.eja.libro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EjaLibroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjaLibroServiceApplication.class, args);
	}

}
