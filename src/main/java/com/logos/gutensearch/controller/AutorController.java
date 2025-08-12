package com.logos.gutensearch.controller;

import java.util.List;
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

    @GetMapping("/filtrar")
    public ResponseEntity<List<Autor>> filtrarAutores(@RequestParam String nome) {
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
