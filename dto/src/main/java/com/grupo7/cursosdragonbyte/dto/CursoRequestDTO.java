package com.grupo7.cursosdragonbyte.dto;

import com.grupo7.cursosdragonbyte.model.enums.CategoriaCurso;
import com.grupo7.cursosdragonbyte.model.enums.DificultadCurso;

public record CursoRequestDTO(
    String nombreCurso,
    String descripcionCurso,
    int numeroNiveles,
    CategoriaCurso categoria,
    DificultadCurso dificultad
) {}
