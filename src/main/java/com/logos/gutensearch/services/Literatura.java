package com.logos.gutensearch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.repository.AutorRepository;
import com.logos.gutensearch.repository.LivroRepository;

@Service
@Transactional(readOnly = true)
public class Literatura {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Literatura(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    
    public List<Livro> filtrarLivros(String titulo, String idioma, String genero) {

        if (titulo != null && !titulo.isEmpty()) {
            return livroRepository.findByTituloContainingIgnoreCase(titulo);
        }
        return livroRepository.findAll();
    }

    @Transactional
    public Livro buscarELancarLivro(String titulo) {
        Optional<Livro> livroOpt = livroRepository.findByTitulo(titulo);
    if (livroOpt.isPresent()) {
        return livroOpt.get();
    } else {
        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo);
        return livroRepository.save(novoLivro);
    }
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
