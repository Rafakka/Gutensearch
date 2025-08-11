package com.logos.gutensearch.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.logos.gutensearch.model.*;

public class GutendexMapper {

    public static Autor toAutor(GutendexAutor gutAutor) {
        if (gutAutor == null) return null;
        return new Autor(
            gutAutor.name(),
            gutAutor.birth_year(),
            gutAutor.death_year()
        );
    }

    public static Livro toLivro(GutendexBookData gutLivro) {
        if (gutLivro == null) return null;

        
        Autor autor = gutLivro.authors().isEmpty() ? null : toAutor(gutLivro.authors().get(0));
        
        String idioma = gutLivro.languages().isEmpty() ? null : gutLivro.languages().get(0);

        return new Livro(
            Long.valueOf(gutLivro.id()),
            gutLivro.title(),
            idioma,
            gutLivro.download_count(),
            null,
            null,
            null,
            autor
        );
    }
    public static List<Livro> toLivroList(List<GutendexBookData> gutLivros) {
        return gutLivros.stream()
                        .map(GutendexMapper::toLivro)
                        .collect(Collectors.toList());
    }
}
