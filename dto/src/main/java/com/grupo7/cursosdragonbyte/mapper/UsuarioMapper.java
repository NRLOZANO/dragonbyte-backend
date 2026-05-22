package com.grupo7.cursosdragonbyte.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.grupo7.cursosdragonbyte.dto.UsuarioResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    UsuarioResponseDTO toResponseDTO(Usuario usuario);


}
