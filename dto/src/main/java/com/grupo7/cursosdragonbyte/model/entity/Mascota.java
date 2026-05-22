package com.grupo7.cursosdragonbyte.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "mascotas")
public class Mascota extends BaseEntity {

@Column(nullable = false, length = 50)
private String nombreMascota;

@Column(nullable = false)
private Integer nivelEvolucion; 

@Column(nullable = false)
private Integer experiencia; 


@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "usuario_id", unique = true, nullable = false) 
@JsonBackReference(value = "usuario-mascota")
private Usuario usuario;

}
