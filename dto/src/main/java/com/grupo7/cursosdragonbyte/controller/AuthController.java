package com.grupo7.cursosdragonbyte.controller;

import com.grupo7.cursosdragonbyte.dto.LoginRequestDTO;
import com.grupo7.cursosdragonbyte.dto.LoginResponseDTO;
import com.grupo7.cursosdragonbyte.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
