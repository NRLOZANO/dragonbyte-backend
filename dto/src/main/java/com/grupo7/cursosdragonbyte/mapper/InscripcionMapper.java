package com.grupo7.cursosdragonbyte.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.grupo7.cursosdragonbyte.dto.InscripcionResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Inscripcion;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InscripcionMapper {

    // Le indicamos que el "nombre" dentro de "usuario" va hacia "nombreUsuario" en el DTO
    @Mapping(source = "usuario.nombre", target = "nombreUsuario")
    InscripcionResponseDTO toResponseDTO(Inscripcion inscripcion);
}