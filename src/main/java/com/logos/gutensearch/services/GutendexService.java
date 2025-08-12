package com.logos.gutensearch.services;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
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
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;

        this.webClient = WebClient.builder()
            .baseUrl("https://gutendex.com/")
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.USER_AGENT, "MeuAppCliente/1.0")
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build())
            .build();
    }

    private Livro mapearParaLivro(GutendexBookData bookData) {
        Livro livro = new Livro();

        livro.setTitulo(bookData.title());

        if (bookData.languages() != null && !bookData.languages().isEmpty()) {
            livro.setIdioma(bookData.languages().get(0));
        }

        livro.setDownloads(bookData.download_count());

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

    public List<Livro> buscarESalvarLivros(String titulo) {
        GutendexResponse response = webClient
            .get()
            .uri(uriBuilder -> uriBuilder.path("/books")
                .queryParam("search", titulo)
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(GutendexResponse.class)
            .block();

        if (response != null && response.results() != null && !response.results().isEmpty()) {
            return response.results().stream()
                .map(this::mapearParaLivro)
                .map(livroRepository::save)
                .toList();
        }

        List<Livro> livrosNoBanco = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (!livrosNoBanco.isEmpty()) {
            return livrosNoBanco;
        }

        throw new RuntimeException("Livro não encontrado");
    }
}
