package com.grupo7.cursosdragonbyte.dto;

public record MascotaResponseDTO(
    Long id,
    String nombreMascota,
    Integer nivelEvolucion,
    Integer experiencia
) {}

