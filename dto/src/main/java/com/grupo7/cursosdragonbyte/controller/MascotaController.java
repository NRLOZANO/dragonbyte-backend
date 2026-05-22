package com.grupo7.cursosdragonbyte.controller;

import com.grupo7.cursosdragonbyte.dto.MascotaRequestDTO;
import com.grupo7.cursosdragonbyte.dto.MascotaResponseDTO;
import com.grupo7.cursosdragonbyte.service.MascotaService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
//@CrossOrigin(origins = "*") para cuando lo conectemos con React
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @Operation(summary = "Listar mascotas", description = "Lista con todas las mascotas registradas en la plataforma.")
    @GetMapping
    public ResponseEntity<List<MascotaResponseDTO>> getAll() {
        return ResponseEntity.ok(mascotaService.findAll());
    }

    @Operation(summary = "Obtener mascota por ID", description = "Busca una mascota según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.findById(id));
    }

    @Operation(summary = "Actualizar nombre de mascota", description = "Actualiza el nombre de una mascota.")
    @PutMapping("/{id}/nombre")
    public ResponseEntity<MascotaResponseDTO> updateName(@PathVariable Long id, @RequestBody MascotaRequestDTO request) {
        return ResponseEntity.ok(mascotaService.updateName(id, request));
    }
}