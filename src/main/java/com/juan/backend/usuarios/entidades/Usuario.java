package com.juan.backend.usuarios.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="El username se encuentra vacio")
    private String userName;
    @Size(min=5,message = "La contraseña es insegura")
    private String password;
    @Email
    private String email;

    @ManyToMany
    @JoinTable(name = "users_roles",//le damos nombre a la tabla intermedia.
            joinColumns = @JoinColumn(name="user_id"),//indica como se debe realizar la union con la tabla que contiene la entidad actual
            inverseJoinColumns = @JoinColumn(name="role_id"),//indica como se debe realizar la union con la tabla relacionada
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})}
    )
    private List<Role> roles;


}
