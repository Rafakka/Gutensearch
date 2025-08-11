package com.logos.gutensearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.services.GutendexService;
import com.logos.gutensearch.services.Literatura;

@RestController
@RequestMapping("/api/livros")
public class LivroController {


    private final GutendexService gutendexService;
    private final Literatura literaturaService;

    public LivroController(GutendexService gutendexService, Literatura literaturaService) {
        this.gutendexService = gutendexService;
        this.literaturaService = literaturaService;
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<Livro>> buscarLivros(@RequestParam String titulo) {
        List<Livro> livros = gutendexService.buscarESalvarLivros(titulo);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Livro>> listarTodosLivros() {
        List<Livro> livros = literaturaService.listarLivrosReg();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/autor")
    public ResponseEntity<Autor> buscarAutor(@RequestParam String nome) {
        Optional<Autor> autor = literaturaService.BuscarAutores(nome);
        return autor.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/autores")
    public ResponseEntity<List<Autor>> listarAutores() {
        return ResponseEntity.ok(literaturaService.listarAutoresRegistrados());
    }

    @GetMapping("/autores/vivos")
    public ResponseEntity<List<Autor>> listarAutoresVivos(@RequestParam int ano) {
        List<Autor> autores = literaturaService.listarAutoresVivosNoAno(ano);
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/idiomas")
    public ResponseEntity<List<String>> listarIdiomas() {
        List<String> idiomas = literaturaService.listarIdiomasLivros();
        return ResponseEntity.ok(idiomas);
    }

    @GetMapping("/contar")
    public ResponseEntity<Integer> contarLivros() {
        int count = literaturaService.contarLivros();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/media-downloads")
    public ResponseEntity<Double> mediaDownloads() {
        double media = literaturaService.mediaDownloadLivros();
        return ResponseEntity.ok(media);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<Livro>> top10Livros() {
        List<Livro> livros = literaturaService.top10Livros();
        return ResponseEntity.ok(livros);
    }
}


