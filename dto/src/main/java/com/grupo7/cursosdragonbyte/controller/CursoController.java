package com.grupo7.cursosdragonbyte.controller;

import com.grupo7.cursosdragonbyte.dto.CursoRequestDTO;
import com.grupo7.cursosdragonbyte.dto.CursoResponseDTO;
import com.grupo7.cursosdragonbyte.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Listar cursos", description = "Lista con todos los cursos disponibles en la plataforma.")
    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> getAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @Operation(summary = "Obtener curso por ID", description = "Busca un curso según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.findById(id));
    }

    @Operation(summary = "Crear curso", description = "Crea un nuevo curso en la plataforma.")
    @PostMapping
    public ResponseEntity<CursoResponseDTO> create(@RequestBody CursoRequestDTO request) {
        return new ResponseEntity<>(cursoService.save(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar curso", description = "Actualiza la información del curso.")
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> update(@PathVariable Long id, @RequestBody CursoRequestDTO request) {
        return ResponseEntity.ok(cursoService.update(id, request));
    }

    @Operation(summary = "Eliminar curso", description = "Elimina un curso de la plataforma.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}