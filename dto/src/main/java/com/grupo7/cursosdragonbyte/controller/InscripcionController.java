package com.grupo7.cursosdragonbyte.controller;

import com.grupo7.cursosdragonbyte.dto.InscripcionRequestDTO;
import com.grupo7.cursosdragonbyte.dto.InscripcionResponseDTO;
import com.grupo7.cursosdragonbyte.service.InscripcionService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Operation(summary = "Listar inscripciones", description = "Lista con todas las inscripciones registradas en la plataforma.")
    @GetMapping
    public ResponseEntity<List<InscripcionResponseDTO>> getAll() {
        return ResponseEntity.ok(inscripcionService.findAll());
    }

    @Operation(summary = "Obtener inscripción por ID", description = "Busca una inscripción según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.findById(id));
    }

    @Operation(summary = "Crear inscripción", description = "Crea una nueva inscripción en la plataforma.")
    @PostMapping
    public ResponseEntity<InscripcionResponseDTO> create(@RequestBody InscripcionRequestDTO request) {
        return new ResponseEntity<>(inscripcionService.save(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar inscripción", description = "Elimina una inscripción de la plataforma.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inscripcionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Completar curso", description = "Marca una inscripción como completada.")
    @PostMapping("/{id}/completar")
    public ResponseEntity<Void> completarCurso(@PathVariable Long id) {
        inscripcionService.completarCurso(id);
        return ResponseEntity.ok().build();
    }
}