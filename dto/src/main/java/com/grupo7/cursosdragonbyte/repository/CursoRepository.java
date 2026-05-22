package com.grupo7.cursosdragonbyte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo7.cursosdragonbyte.model.entity.Curso;
import com.grupo7.cursosdragonbyte.model.enums.CategoriaCurso;
import com.grupo7.cursosdragonbyte.model.enums.DificultadCurso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

List<Curso> findByCategoria(CategoriaCurso categoria);
    
List<Curso> findByDificultad(DificultadCurso dificultad);

}
