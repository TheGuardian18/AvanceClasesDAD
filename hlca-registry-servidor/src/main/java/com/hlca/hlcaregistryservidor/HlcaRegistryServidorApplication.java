package com.hlca.hlcaregistryservidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HlcaRegistryServidorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HlcaRegistryServidorApplication.class, args);
    }

}
