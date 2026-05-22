package com.grupo7.cursosdragonbyte.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UbicacionUsuario {

private String pais;
private String departamento;
private String ciudad;

}
