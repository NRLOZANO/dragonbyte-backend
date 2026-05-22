package com.grupo7.cursosdragonbyte.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grupo7.cursosdragonbyte.model.enums.EstadoInscripcion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "inscripciones")

public class Inscripcion extends BaseEntity {
    
@Enumerated(EnumType.STRING)
@Column(nullable = false, length = 20)
private EstadoInscripcion estado;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "usuario_id", nullable = false)
@JsonBackReference(value = "usuario-inscripcion") 
private Usuario usuario;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "curso_id", nullable = false)
@JsonBackReference(value = "curso-inscripcion") 
private Curso curso;
}

