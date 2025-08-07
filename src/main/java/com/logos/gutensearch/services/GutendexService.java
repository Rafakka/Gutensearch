package com.logos.gutensearch.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.logos.gutensearch.dto.GutendexAutor;
import com.logos.gutensearch.dto.GutendexBookData;
import com.logos.gutensearch.dto.GutendexResponse;
import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.model.Livro;

@Service
public class GutendexService {

    private static final String API_URL = "https://gutendex.com/books/";
    private static final String DEFAULT_LANGUAGE = "desconhecido";
    private static final String DEFAULT_TITLE = "Sem título";
    private static final int DEFAULT_DOWNLOADS = 0;

    private final RestTemplate restTemplate;

    public GutendexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Livro> buscarLivros(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título não pode ser vazio");
        }

        String url = API_URL + "?search=" + titulo.replace(" ", "%20");
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

        if (response == null || response.results() == null) {
            return List.of();
        }

        return response.results().stream()
            .filter(Objects::nonNull)
            .map(this::converterDados)
            .collect(Collectors.toList());
    }

     private Livro converterDados(GutendexBookData dados) {
        Objects.requireNonNull(dados, "Dados do livro não podem ser nulos");

        Autor autor = new Autor();
        if (dados.authors() != null && !dados.authors().isEmpty()) {
            GutendexAutor autorApi = dados.authors().get(0);
            if (autorApi != null) {
                autor.setNome(autorApi.name() != null ? autorApi.name() : "Autor desconhecido");
                autor.setAnoNascimento(autorApi.birth_year());
                autor.setAnoFalecimento(autorApi.death_year());
            }
        }

        Livro livro = new Livro();
        livro.setTitulo(dados.title() != null ? dados.title() : DEFAULT_TITLE);
        livro.setIdioma(dados.languages() != null && !dados.languages().isEmpty() 
            ? dados.languages().get(0) 
            : DEFAULT_LANGUAGE);
        livro.setDownloads(dados.download_count() != null ? dados.download_count() : DEFAULT_DOWNLOADS);
        livro.setAutor(autor);

        return livro;
    }
}
    