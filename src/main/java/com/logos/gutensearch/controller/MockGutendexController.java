package com.logos.gutensearch.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logos.gutensearch.dto.GutendexAutor;
import com.logos.gutensearch.dto.GutendexBookData;
import com.logos.gutensearch.dto.GutendexResponse;

@RestController
public class MockGutendexController {

    @GetMapping("/books/")
    public GutendexResponse mockBooks() {
        GutendexBookData livro1 = new GutendexBookData(
            1,
            "Dom Casmurro",
            List.of("pt"),
            1000,
            List.of(new GutendexAutor("Machado de Assis", 1839, 1908))
        );

        GutendexBookData livro2 = new GutendexBookData(
            2,
            "Emma",
            List.of("en"),
            1500,
            List.of(new GutendexAutor("Jane Austen", 1775, 1817))
        );

        List<GutendexBookData> livros = List.of(livro1, livro2);

        return new GutendexResponse(
            livros.size(),
            null,
            null,
            livros
        );
    }
}