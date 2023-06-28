package com.juan.backend.usuarios.servicios;

import com.juan.backend.usuarios.entidades.Usuario;
import com.juan.backend.usuarios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepo.findById(id);
    }

    @Override
    @Transactional
    public Usuario Save(Usuario us) {

        return usuarioRepo.save(us);

    }

    @Override
    @Transactional
    public void remove(Long id){
        usuarioRepo.deleteById(id);
    }
}
