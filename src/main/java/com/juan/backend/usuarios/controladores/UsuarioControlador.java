package com.juan.backend.usuarios.controladores;

import com.juan.backend.usuarios.entidades.Usuario;
import com.juan.backend.usuarios.servicios.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(originPatterns = "http://localhost:5173")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicioUs;

    @GetMapping("/all")
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok(servicioUs.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id){
        Optional<Usuario> resp = servicioUs.findById(id);
        if(!resp.isEmpty()){
            Usuario us = resp.get();
            return ResponseEntity.ok(us);//devuelve un 200
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?>create(@Valid @RequestBody Usuario us, BindingResult result){

        if(result.hasErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioUs.Save(us));//devuelve un 201

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>update(@Valid @RequestBody Usuario us,BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validation(result);
        }
        Optional<Usuario> resp = servicioUs.findById(id);
        if(! resp.isEmpty()){
          Usuario usuario = resp.get();
          usuario.setUserName(us.getUserName());
          usuario.setEmail(us.getEmail());
          return ResponseEntity.status(HttpStatus.CREATED).body(servicioUs.Save(usuario));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>remove(@PathVariable Long id){
        Optional<Usuario> resp = servicioUs.findById(id);
        if(!resp.isEmpty()){
            servicioUs.remove(id);
            return ResponseEntity.noContent().build();//devuelve un 204
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);//badRequest devuelve un stado 400: indica que la solicitud enviada por el cliente es incorrecta
        }
    }


