package com.eja.usuario.Service.ServiceImpl;


import com.eja.usuario.Entity.Usuario;
import com.eja.usuario.Repository.UsuarioRepositorio;
import com.eja.usuario.Service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> Listar() {
        return usuarioRepositorio.findAll();
    }

    @Override
    public Optional<Usuario> Buscar(Long id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public Usuario Guardar(Usuario usuario) {
        if (usuarioRepositorio.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está en uso");
        }
        return usuarioRepositorio.save(usuario);
    }
    @Override
    public Usuario Modificar(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public void Eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}