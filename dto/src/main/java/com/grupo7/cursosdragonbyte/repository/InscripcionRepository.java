package com.grupo7.cursosdragonbyte.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo7.cursosdragonbyte.model.entity.Inscripcion;
import com.grupo7.cursosdragonbyte.model.enums.EstadoInscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    // Quiero que me liste los cursos de un usuario específico
    List<Inscripcion> findByUsuarioId(Long usuarioId);
    
    // Quiero que me liste los usuarios inscritos en un curso específico
    List<Inscripcion> findByCursoId(Long cursoId);
    
    // Buscar una inscripción específica por usuario y curso
    boolean findByUsuarioIdAndCursoId(Long usuarioId, Long cursoId);
    
    // Quiero que me liste los cursos activos o denegados de un usuario
    List<Inscripcion> findByUsuarioIdAndEstado(Long usuarioId, EstadoInscripcion estado);

}
