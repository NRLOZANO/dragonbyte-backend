package com.grupo7.cursosdragonbyte.service;

import com.grupo7.cursosdragonbyte.dto.CursoRequestDTO;
import com.grupo7.cursosdragonbyte.dto.CursoResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Curso;
import com.grupo7.cursosdragonbyte.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoResponseDTO> findAll() {
        return cursoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CursoResponseDTO findById(Long id) {
        return convertToDTO(findEntity(id));
    }

    @Transactional
    public CursoResponseDTO save(CursoRequestDTO request) {
        Curso curso = convertToEntity(request);
        return convertToDTO(cursoRepository.save(curso));
    }

    @Transactional
    public CursoResponseDTO update(Long id, CursoRequestDTO request) {
        Curso curso = findEntity(id);
        curso.setNombreCurso(request.nombreCurso());
        curso.setDescripcionCurso(request.descripcionCurso());
        curso.setNumeroNiveles(request.numeroNiveles());
        curso.setCategoria(request.categoria());
        curso.setDificultad(request.dificultad());
        
        return convertToDTO(cursoRepository.save(curso));
    }

    @Transactional
    public void delete(Long id) {
        findEntity(id);
        cursoRepository.deleteById(id);
    }

   

    public CursoResponseDTO convertToDTO(Curso curso) {
        return new CursoResponseDTO(
                curso.getId(),
                curso.getNombreCurso(),
                curso.getDescripcionCurso(),
                curso.getNumeroNiveles(),
                curso.getCategoria(),
                curso.getDificultad()
        );
    }

    private Curso convertToEntity(CursoRequestDTO dto) {
        Curso curso = new Curso();
        curso.setNombreCurso(dto.nombreCurso());
        curso.setDescripcionCurso(dto.descripcionCurso());
        curso.setNumeroNiveles(dto.numeroNiveles());
        curso.setCategoria(dto.categoria());
        curso.setDificultad(dto.dificultad());
        return curso;
    }

    public Curso findEntity(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Curso con id: " + id + " no encontrado"));
    }
}
