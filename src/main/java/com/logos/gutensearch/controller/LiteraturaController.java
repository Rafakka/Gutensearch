package com.logos.gutensearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logos.gutensearch.model.Autor;
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

    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> listarLivrosReg() {
        List<Livro> livros = literaturaService.listarLivrosReg();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/autores")
    public ResponseEntity<List<Autor>> listarAutoresRegistrados() {
        List<Autor> autores = literaturaService.listarAutoresRegistrados();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/autor/buscar")
    public ResponseEntity<Autor> buscarAutorPorNome(@RequestParam String nome) {
        Optional<Autor> autor = literaturaService.BuscarAutores(nome);
        return autor.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
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

    @GetMapping("/autores/filtrar-nome-parcial")
    public ResponseEntity<List<Autor>> filtrarAutoresPorNome(@RequestParam String nome) {
        List<Autor> autores = literaturaService.filtrarAutoresPorNome(nome);
        return ResponseEntity.ok(autores);
    }
}
