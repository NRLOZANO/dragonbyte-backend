package com.grupo7.cursosdragonbyte.service;

import com.grupo7.cursosdragonbyte.dto.LoginRequestDTO;
import com.grupo7.cursosdragonbyte.dto.LoginResponseDTO;
import com.grupo7.cursosdragonbyte.model.entity.Usuario;
import com.grupo7.cursosdragonbyte.repository.UsuarioRepository;
import com.grupo7.cursosdragonbyte.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().name());
        return new LoginResponseDTO(token, usuario.getRol().name(), usuario.getNombre());
    }
}
