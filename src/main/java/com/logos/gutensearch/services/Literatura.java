package com.logos.gutensearch.services;

import java.util.Collections;
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
    public List <Livro> buscarELancarLivro(String titulo) {
        List <Livro> resultadosApi = gutendexService.buscarESalvarLivros(titulo);

        if (!resultadosApi.isEmpty()){
            return resultadosApi;
        } 

        List <Livro> livroBanco = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (!livroBanco.isEmpty()){
            return livroBanco;
        }

        return Collections.emptyList();
    }

    public List<Autor> listarAutoresComObras(){
        return autorRepository.findAllAutoresComObras();
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
