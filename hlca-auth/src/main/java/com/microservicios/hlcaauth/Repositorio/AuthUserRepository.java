package com.microservicios.hlcaauth.Repositorio;

import com.microservicios.hlcaauth.Entidad.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthUserRepository extends JpaRepository<AuthUser,Integer> {
    AuthUser findByUsername(String username);
}
