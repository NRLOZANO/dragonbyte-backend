package com.grupo7.cursosdragonbyte.model.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grupo7.cursosdragonbyte.model.enums.CategoriaCurso;
import com.grupo7.cursosdragonbyte.model.enums.DificultadCurso;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cursos")

public class Curso extends BaseEntity {

@Column(nullable = false, length = 100)
private String nombreCurso;

@Column(length = 500)
private String descripcionCurso;

@Column(nullable = false)
private int numeroNiveles;

@Enumerated(EnumType.STRING)
@Column(nullable = false, length = 5)
private CategoriaCurso categoria;

@Enumerated (EnumType.STRING)
@Column(nullable = false, length = 10)
private DificultadCurso dificultad;

@OneToMany(mappedBy = "curso")
@JsonManagedReference(value = "curso-inscripcion") 
private List<Inscripcion> inscripciones;

}
