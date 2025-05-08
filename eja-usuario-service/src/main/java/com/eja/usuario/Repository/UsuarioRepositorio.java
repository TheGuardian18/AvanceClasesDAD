package com.eja.usuario.Repository;


import com.eja.usuario.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    boolean existsByCorreo(String correo);
}
