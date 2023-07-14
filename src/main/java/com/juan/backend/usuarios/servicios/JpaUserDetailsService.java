package com.juan.backend.usuarios.servicios;


import com.juan.backend.usuarios.entidades.Usuario;
import com.juan.backend.usuarios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepositorio usRepo;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> resp = usRepo.findByUserName(userName);
        if(!resp.isEmpty()){
            Usuario u = resp.get();
            List<GrantedAuthority> authorities = u.getRoles()
                    .stream()
                    .map(r -> new SimpleGrantedAuthority(r.getRol()) )
                    .collect(Collectors.toList());

            return new User(u.getUserName(),u.getPassword(),true,true,true,true,authorities);
        }

        throw new UsernameNotFoundException("no existe");
    }
}
