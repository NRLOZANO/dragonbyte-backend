package com.grupo7.cursosdragonbyte.model.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grupo7.cursosdragonbyte.model.embeddable.UbicacionUsuario;
import com.grupo7.cursosdragonbyte.model.enums.RolUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
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
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String apellido;

    @Column(length = 20, nullable = true)
    private String genero;

    @Column
    private Integer edad;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RolUsuario rol;

    @Embedded
    private UbicacionUsuario ubicacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "usuario-inscripcion")
    private List<Inscripcion> inscripciones;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "usuario-mascota")
    private Mascota mascota;
}
