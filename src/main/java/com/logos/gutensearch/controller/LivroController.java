package com.logos.gutensearch.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Livro>> listarLivrosReg() {
        List<Livro> livros = literaturaService.listarLivrosReg();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/livros/buscar-elancar")
    public ResponseEntity<Livro> buscarELancarLivro(@RequestParam String titulo) {
        List<Livro> livros = literaturaService.buscarELancarLivro(titulo);
        if (livros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livros.get(0));
    }

    @GetMapping("/estatisticas/idiomas")
    public ResponseEntity<List<String>> listarIdiomasLivros() {
        List<String> idiomas = literaturaService.listarIdiomasLivros();
        return ResponseEntity.ok(idiomas);
    }

    @GetMapping("/estatisticas/contar")
    public ResponseEntity<Integer> contarLivros() {
        int count = literaturaService.contarLivros();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/estatisticas/media-downloads")
    public ResponseEntity<Double> mediaDownloadLivros() {
        Double media = literaturaService.mediaDownloadLivros();
        return ResponseEntity.ok(media);
    }

    @GetMapping("/estatisticas/top10")
    public ResponseEntity<List<Livro>> top10Livros() {
        List<Livro> livros = literaturaService.top10Livros();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Livro>> filtrarLivros(
        @RequestParam(required = false) String titulo,
        @RequestParam(required = false) String autor,
        @RequestParam(required = false) String idioma) {

    List<Livro> livros = literaturaService.filtrarLivros(titulo, autor, idioma);

    if ((livros.isEmpty() || livros == null) && titulo != null && !titulo.isBlank()) {
        List<Livro> novosLivros = gutendexService.buscarESalvarLivros(titulo);
        livros.addAll(novosLivros);
    }

    return ResponseEntity.ok(livros);
}

}


