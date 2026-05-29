package com.grupo7.cursosdragonbyte.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/graficos")
public class GraficoController {

    private final RestTemplate restTemplate;

    @Value("${python.service.url}")
    private String pythonServiceUrl;

    public GraficoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/headless/usuarios-por-curso")
    public ResponseEntity<Map<String, Object>> usuariosPorCursoHeadless() {
        String url = pythonServiceUrl + "/api/graficos/headless/usuarios-por-curso";
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/headless/cursos-mas-populares")
    public ResponseEntity<Map<String, Object>> cursosMasPopularesHeadless(@RequestParam(defaultValue = "5") int top) {
        String url = pythonServiceUrl + "/api/graficos/headless/cursos-mas-populares?top=" + top;
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/pull/usuarios-por-curso", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> usuariosPorCursoPull() {
        String url = pythonServiceUrl + "/api/graficos/pull/usuarios-por-curso";
        byte[] image = restTemplate.getForObject(url, byte[].class);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @GetMapping(value = "/pull/cursos-mas-populares", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> cursosMasPopularesPull(@RequestParam(defaultValue = "5") int top) {
        String url = pythonServiceUrl + "/api/graficos/pull/cursos-mas-populares?top=" + top;
        byte[] image = restTemplate.getForObject(url, byte[].class);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @GetMapping("/analisis/usuarios")
    public ResponseEntity<Map<String, Object>> analisisUsuarios() {
        String url = pythonServiceUrl + "/api/analisis/usuarios";
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/analisis/cursos")
    public ResponseEntity<Map<String, Object>> analisisCursos() {
        String url = pythonServiceUrl + "/api/analisis/cursos";
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return ResponseEntity.ok(response);
    }
}
