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


