package com.logos.gutensearch.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.repository.AutorRepository;
import com.logos.gutensearch.repository.LivroRepository;
import com.logos.gutensearch.repository.LivroSpecification;

import org.springframework.data.jpa.domain.Specification;

@Service
@Transactional(readOnly = true)
public class Literatura {

    private final LivroRepository livroRepository;
    private final GutendexService gutendexService;
    private final AutorRepository autorRepository;

    public Literatura(LivroRepository livroRepository,GutendexService gutendexService, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.gutendexService = gutendexService;
        this.autorRepository = autorRepository;
    }

    
    public List<Livro> filtrarLivros(String titulo, String idioma, String genero) {
        Specification<Livro> spec =
        LivroSpecification.filtroDinamico(titulo, idioma, genero);
        return livroRepository.findAll(spec);
    }

    @Transactional
    public Livro buscarELancarLivro(String titulo) {
        List <Livro> resultadosApi = gutendexService.buscarESalvarLivros(titulo);

        if (resultadosApi != null && !resultadosApi.isEmpty()) {
            Livro livroApi = resultadosApi.get(0);
            livroRepository.save(livroApi);
            return livroApi;
        } 

        Optional<Livro> livroBanco = livroRepository.findFirstByTituloContainingIgnoreCase(titulo);
        if (livroBanco.isPresent()){
            return livroBanco.get();
        }
        System.out.println("Livro não encontrado");
        return null;

    }

    public List<Livro> listarLivrosReg() {
        return livroRepository.findAll();
    }

    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAll();
    }

    public Optional<Autor> BuscarAutores(String nome) {
        return autorRepository.findByNome(nome);
    }

    public List<Autor> listarAutoresVivosNoAno(int ano) {
        return autorRepository.findByAnoFalecimentoIsNullOrAnoFalecimentoAfter(ano);
    }

    public List<String> listarIdiomasLivros() {
        return livroRepository.findDistinctIdiomas();
    }

    public int contarLivros() {
        return (int) livroRepository.count();
    }

    public Double mediaDownloadLivros() {
        return livroRepository.mediaDownloadLivros();
    }

    public List<Livro> top10Livros() {
        return livroRepository.findTop10ByOrderByDownloadsDesc();
    }


    public List<Autor> filtrarAutoresPorNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }
}
