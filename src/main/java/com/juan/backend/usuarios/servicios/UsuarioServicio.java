package com.juan.backend.usuarios.servicios;

import com.juan.backend.usuarios.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario Save(Usuario us);

    void remove(Long id);
}
