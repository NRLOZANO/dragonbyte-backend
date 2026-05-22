package com.grupo7.cursosdragonbyte.service;

import com.grupo7.cursosdragonbyte.dto.InscripcionRequestDTO;
import com.grupo7.cursosdragonbyte.dto.InscripcionResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Curso;
import com.grupo7.cursosdragonbyte.model.entity.Inscripcion;
import com.grupo7.cursosdragonbyte.model.entity.Mascota;
import com.grupo7.cursosdragonbyte.model.entity.Usuario;
import com.grupo7.cursosdragonbyte.model.enums.EstadoInscripcion;
import com.grupo7.cursosdragonbyte.repository.InscripcionRepository;
import com.grupo7.cursosdragonbyte.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<InscripcionResponseDTO> findAll() {
        return inscripcionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public InscripcionResponseDTO findById(Long id) {
        return convertToDTO(findEntity(id));
    }

    @Transactional
    public InscripcionResponseDTO save(InscripcionRequestDTO request) {
       
        Usuario usuario = usuarioService.findEntity(request.usuarioId());
        Curso curso = cursoService.findEntity(request.cursoId());

        if (inscripcionRepository.findByUsuarioIdAndCursoId(usuario.getId(), curso.getId())) {
            throw new IllegalArgumentException("El jugador ya está inscrito en este curso");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(usuario);
        inscripcion.setCurso(curso);
        inscripcion.setEstado(EstadoInscripcion.ACTIVO);

        return convertToDTO(inscripcionRepository.save(inscripcion));
    }

    @Transactional
    public void delete(Long id) {
        findEntity(id);
        inscripcionRepository.deleteById(id);
    }

    
    @Transactional
    public void completarCurso(Long id) {
        Inscripcion inscripcion = findEntity(id);
        
        Usuario jugador = inscripcion.getUsuario();
        Mascota mascota = jugador.getMascota();
        
        if (mascota != null) {
            int experienciaGanada = calcularExperiencia(inscripcion.getCurso());
            mascota.setExperiencia(mascota.getExperiencia() + experienciaGanada);

            if (mascota.getExperiencia() >= (mascota.getNivelEvolucion() * 100)) {
                mascota.setNivelEvolucion(mascota.getNivelEvolucion() + 1);
            }
            
            mascotaRepository.save(mascota);
        }
    }

    private int calcularExperiencia(Curso curso) {
        return switch (curso.getDificultad()) {
            case BASICO -> 50;
            case INTERMEDIO -> 100;
            case AVANZADO -> 200;
        };
    }



    private InscripcionResponseDTO convertToDTO(Inscripcion inscripcion) {
        return new InscripcionResponseDTO(
                inscripcion.getId(),
                inscripcion.getEstado(),
                inscripcion.getUsuario().getNombre(),
                cursoService.convertToDTO(inscripcion.getCurso()) 
        );
    }

    public Inscripcion findEntity(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Inscripción con id: " + id + " no encontrada"));
    }
}
