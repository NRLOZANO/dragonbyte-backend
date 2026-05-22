package com.grupo7.cursosdragonbyte.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.grupo7.cursosdragonbyte.dto.CursoResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Curso;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CursoMapper {
    CursoResponseDTO toResponseDTO(Curso curso);

}
