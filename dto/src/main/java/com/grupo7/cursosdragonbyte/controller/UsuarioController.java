package com.grupo7.cursosdragonbyte.controller;

import com.grupo7.cursosdragonbyte.dto.UsuarioRequestDTO;
import com.grupo7.cursosdragonbyte.dto.UsuarioResponseDTO;
import com.grupo7.cursosdragonbyte.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar usuarios", description = "Lista con todos los usuarios registrados en la plataforma.")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Operation(summary = "Obtener usuario por ID", description = "Busca un usuario según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario en la plataforma.")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO request) {
        return new ResponseEntity<>(usuarioService.save(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza la información del usuario.")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @RequestBody UsuarioRequestDTO request) {
        return ResponseEntity.ok(usuarioService.update(id, request));
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario de la plataforma.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}