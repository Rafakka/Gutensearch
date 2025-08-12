package com.logos.gutensearch.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.services.Literatura;

@RestController
@RequestMapping("/api")
public class LiteraturaController {

    private final Literatura literaturaService;

    public LiteraturaController(Literatura literaturaService) {
        this.literaturaService = literaturaService;
    }

    @GetMapping("/livros/buscar-elancar")
    public ResponseEntity<Livro> buscarELancarLivro(@RequestParam String titulo) {
        Livro livro = literaturaService.buscarELancarLivro(titulo);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/livros/idiomas")
    public ResponseEntity<List<String>> listarIdiomasLivros() {
        List<String> idiomas = literaturaService.listarIdiomasLivros();
        return ResponseEntity.ok(idiomas);
    }

    @GetMapping("/livros/contar")
    public ResponseEntity<Integer> contarLivros() {
        int count = literaturaService.contarLivros();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/livros/media-downloads")
    public ResponseEntity<Double> mediaDownloadLivros() {
        Double media = literaturaService.mediaDownloadLivros();
        return ResponseEntity.ok(media);
    }

    @GetMapping("/livros/top10")
    public ResponseEntity<List<Livro>> top10Livros() {
        List<Livro> livros = literaturaService.top10Livros();
        return ResponseEntity.ok(livros);
    }


}
