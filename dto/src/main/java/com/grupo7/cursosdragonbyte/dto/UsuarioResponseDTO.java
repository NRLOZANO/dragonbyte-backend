package com.grupo7.cursosdragonbyte.dto;

import com.grupo7.cursosdragonbyte.model.enums.RolUsuario;

public record UsuarioResponseDTO(
    Long id,
    String nombre,
    String apellido,
    String email,
    RolUsuario rol,
    MascotaResponseDTO mascota
) {}