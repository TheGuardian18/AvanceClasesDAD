package com.hlca.hlcaconfigservidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HlcaConfigServidorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HlcaConfigServidorApplication.class, args);
    }

}
