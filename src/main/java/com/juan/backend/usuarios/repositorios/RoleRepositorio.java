package com.juan.backend.usuarios.repositorios;

import com.juan.backend.usuarios.entidades.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositorio extends JpaRepository<Role,Long>{

    Optional<Role> findByRol(String rol);
}
