package com.juan.backend.usuarios.servicios;


import com.juan.backend.usuarios.entidades.Role;
import com.juan.backend.usuarios.entidades.Usuario;
import com.juan.backend.usuarios.repositorios.RoleRepositorio;
import com.juan.backend.usuarios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private RoleRepositorio roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        us.setPassword(passwordEncoder.encode(us.getPassword()));

        Optional<Role> o = roleRepo.findByRol("ROLE_USER");
        List <Role> permisos = new ArrayList<>();
        if(o.isPresent()){
            permisos.add(o.get());
        }
        us.setRoles(permisos);
        return usuarioRepo.save(us);
    }

    @Override
    @Transactional
    public void remove(Long id){
        usuarioRepo.deleteById(id);
    }
}
