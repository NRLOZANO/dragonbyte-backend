package com.grupo7.cursosdragonbyte.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo7.cursosdragonbyte.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//Para cuando usemos el formulario de loggin
Optional<Usuario> findByEmail(String email);
    
boolean existsByEmail(String email);

}
