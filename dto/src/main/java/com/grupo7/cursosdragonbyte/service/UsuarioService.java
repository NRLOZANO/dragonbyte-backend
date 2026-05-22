package com.grupo7.cursosdragonbyte.service;

import com.grupo7.cursosdragonbyte.dto.UsuarioRequestDTO;
import com.grupo7.cursosdragonbyte.dto.UsuarioResponseDTO;
import com.grupo7.cursosdragonbyte.dto.MascotaResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Mascota;
import com.grupo7.cursosdragonbyte.model.embeddable.UbicacionUsuario;
import com.grupo7.cursosdragonbyte.model.entity.Usuario;
import com.grupo7.cursosdragonbyte.model.enums.RolUsuario;
import com.grupo7.cursosdragonbyte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO findById(Long id) {
        return convertToDTO(findEntity(id));
    }

    @Transactional
    public UsuarioResponseDTO save(UsuarioRequestDTO request) {
    if (usuarioRepository.existsByEmail(request.email())) {
        throw new IllegalArgumentException("El correo ya está registrado");
    }

    Usuario usuario = convertToEntity(request);

    if (usuario.getRol() == RolUsuario.JUGADOR) {
        Mascota mascotaInicial = new Mascota();
        mascotaInicial.setNombreMascota("Reactzilla");
        mascotaInicial.setNivelEvolucion(1);
        mascotaInicial.setExperiencia(0);
        mascotaInicial.setUsuario(usuario);
        usuario.setMascota(mascotaInicial);
    }

    return convertToDTO(usuarioRepository.save(usuario));
}

    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO request) {
        Usuario usuario = findEntity(id);
        usuario.setNombre(request.nombre());
        usuario.setApellido(request.apellido());
        usuario.setGenero(request.genero());
        usuario.setEdad(request.edad());
        usuario.setRol(request.rol());
        usuario.setUbicacion(request.ubicacion());
        
        
        return convertToDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public void delete(Long id) {
        findEntity(id);
        usuarioRepository.deleteById(id);
    }

  

    private UsuarioResponseDTO convertToDTO(Usuario usuario) {
        MascotaResponseDTO mascotaDTO = null;
        if (usuario.getMascota() != null) {
            mascotaDTO = new MascotaResponseDTO(
                    usuario.getMascota().getId(),
                    usuario.getMascota().getNombreMascota(),
                    usuario.getMascota().getNivelEvolucion(),
                    usuario.getMascota().getExperiencia()
            );
        }

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getRol(),
                mascotaDTO
        );
    }

    //private Usuario convertToEntity(UsuarioRequestDTO dto) {
        //Usuario usuario = new Usuario();
        //usuario.setNombre(dto.nombre());
        //usuario.setApellido(dto.apellido());
        //usuario.setGenero(dto.genero());
        //usuario.setEdad(dto.edad());
        //usuario.setEmail(dto.email());
        //usuario.setPassword(dto.password());
        //usuario.setRol(dto.rol());
        //usuario.setUbicacion(dto.ubicacion());
        //return usuario;
    //}

    private Usuario convertToEntity(UsuarioRequestDTO dto) {
    Usuario usuario = new Usuario();
    usuario.setNombre(dto.nombre());
    usuario.setApellido(dto.apellido());
    usuario.setGenero(dto.genero());
    usuario.setEdad(dto.edad());
    usuario.setEmail(dto.email());
    usuario.setPassword(dto.password());
    usuario.setRol(dto.rol());
    
    // --- Mapeo del objeto @Embedded ---
    if (dto.ubicacion() != null) {
        // Instanciamos la clase embebida de la entidad
        UbicacionUsuario embeddableUbicacion = new UbicacionUsuario();
        
        // Pasamos los datos desde tu DTO hacia el Embeddable
        embeddableUbicacion.setPais(dto.ubicacion().getPais());
        embeddableUbicacion.setDepartamento(dto.ubicacion().getDepartamento());
        embeddableUbicacion.setCiudad(dto.ubicacion().getCiudad());
        
        // Finalmente se lo asignamos al usuario
        usuario.setUbicacion(embeddableUbicacion);
    }
    
    return usuario;
}

    public Usuario findEntity(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));
    }
}