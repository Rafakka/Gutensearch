package com.logos.gutensearch.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.logos.gutensearch.dto.GutendexBookData;
import com.logos.gutensearch.dto.GutendexResponse;
import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.repository.AutorRepository;
import com.logos.gutensearch.repository.LivroRepository;

@Service
public class GutendexService {

    private final WebClient webClient;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public GutendexService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.webClient = WebClient.create("https://gutendex.com");
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    private Livro mapearParaLivro(GutendexBookData bookData) {
    Livro livro = new Livro();

    livro.setTitulo(bookData.title());

    if (bookData.languages() != null && !bookData.languages().isEmpty()) {
        livro.setIdioma(bookData.languages().get(0));
    }

    livro.setDownloads(bookData.download_count());
    livro.setDataPublicacao(null);
    Autor autor = null;
    if (bookData.authors() != null && !bookData.authors().isEmpty()) {
        String nomeAutor = bookData.authors().get(0).name();
        autor = autorRepository.findByNome(nomeAutor)
                .orElseGet(() -> {
                    Autor novoAutor = new Autor();
                    novoAutor.setNome(nomeAutor);
                    return autorRepository.save(novoAutor);
                });
    }
    livro.setAutor(autor);

    return livro;
    }

    public List<Livro> buscarLivros(String termoBusca) {
        GutendexResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/books")
                        .queryParam("search", termoBusca)
                        .build())
                .retrieve()
                .bodyToMono(GutendexResponse.class)
                .block();

        List<Livro> livrosSalvos = new ArrayList<>();

        if (response != null && response.results() != null) {
            for (GutendexBookData bookData : response.results()) {
            Livro livro = mapearParaLivro(bookData);

            if (livro.getAutor() != null) {
                boolean existe = livroRepository
                        .findByTituloAndAutorNome(
                            livro.getTitulo(), 
                            livro.getAutor().getNome()
                        )
                        .isPresent();

                if (!existe) {
                    livrosSalvos.add(livroRepository.save(livro));
                    }
                }
            }
        }
        return livrosSalvos;
    }
}