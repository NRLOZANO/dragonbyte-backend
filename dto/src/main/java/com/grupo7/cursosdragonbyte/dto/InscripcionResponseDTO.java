package com.grupo7.cursosdragonbyte.dto;

import com.grupo7.cursosdragonbyte.model.enums.EstadoInscripcion;

public record InscripcionResponseDTO(
    Long id,
    EstadoInscripcion estado,
    String nombreUsuario,
    CursoResponseDTO curso
) {}


