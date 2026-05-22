package com.grupo7.cursosdragonbyte.dto;

import com.grupo7.cursosdragonbyte.model.embeddable.UbicacionUsuario;
import com.grupo7.cursosdragonbyte.model.enums.RolUsuario;

public record UsuarioRequestDTO(
    String nombre,
    String apellido,
    String genero,
    Integer edad,
    String email,
    String password,
    RolUsuario rol,
    UbicacionUsuario ubicacion
) {}
