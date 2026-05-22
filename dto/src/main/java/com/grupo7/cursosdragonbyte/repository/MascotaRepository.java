package com.grupo7.cursosdragonbyte.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo7.cursosdragonbyte.model.entity.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository <Mascota, Long> {

Optional<Mascota> findByUsuarioId(Long usuarioId);

}
