package com.grupo7.cursosdragonbyte.service;

import com.grupo7.cursosdragonbyte.dto.MascotaRequestDTO;
import com.grupo7.cursosdragonbyte.dto.MascotaResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Mascota;
import com.grupo7.cursosdragonbyte.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<MascotaResponseDTO> findAll() {
        return mascotaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MascotaResponseDTO findById(Long id) {
        return convertToDTO(findEntity(id));
    }

    @Transactional
    public MascotaResponseDTO updateName(Long id, MascotaRequestDTO request) {
        Mascota mascota = findEntity(id);
        mascota.setNombreMascota(request.nombreMascota());
        return convertToDTO(mascotaRepository.save(mascota));
    }

    

    private MascotaResponseDTO convertToDTO(Mascota mascota) {
        return new MascotaResponseDTO(
                mascota.getId(),
                mascota.getNombreMascota(),
                mascota.getNivelEvolucion(),
                mascota.getExperiencia()
        );
    }

    public Mascota findEntity(Long id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Mascota con id: " + id + " no encontrada"));
    }
}
