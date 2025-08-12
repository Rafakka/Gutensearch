package com.logos.gutensearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.services.Literatura;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final Literatura literaturaService;

    public AutorController(Literatura literaturaService) {
        this.literaturaService = literaturaService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<Autor> buscarAutorPorNome(@RequestParam String nome) {
        Optional<Autor> autor = literaturaService.BuscarAutores(nome);
        return autor.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtrar-nome-parcial")
    public ResponseEntity<List<Autor>> filtrarAutoresPorNome(@RequestParam String nome) {
        List<Autor> autores = literaturaService.filtrarAutoresPorNome(nome);
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Autor>> listarAutores() {
        List<Autor> autores = literaturaService.listarAutoresRegistrados();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/vivos")
    public ResponseEntity<List<Autor>> autoresVivos(@RequestParam int ano) {
        List<Autor> autores = literaturaService.listarAutoresVivosNoAno(ano);
        return ResponseEntity.ok(autores);
    }
}
