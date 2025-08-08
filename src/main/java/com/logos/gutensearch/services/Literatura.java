package com.logos.gutensearch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.repository.AutorRepository;
import com.logos.gutensearch.repository.LivroRepository;

@Service
public class Literatura {
    @Autowired
    private GutendexService gutendexService;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    public void buscarELancarLivro(String titulo) {
        List<Livro> livros = gutendexService.buscarLivros(titulo);
        
        livros.forEach(livro -> {
            Optional<Livro> livroExistente = livroRepository.findByTitulo(livro.getTitulo());
            if (livroExistente.isEmpty()) {
                Autor autor = livro.getAutor();
                Optional<Autor> autorExistente = autorRepository.findByNome(autor.getNome());
                
                if (autorExistente.isPresent()) {
                    livro.setAutor(autorExistente.get());
                } else {
                    autorRepository.save(autor);
                }
                
                livroRepository.save(livro);
            }
        });
    }

    public List<Livro> listarLivrosReg() {
        return livroRepository.findAll();
    }

    public Optional<Autor> BuscarAutores(String nome) {
        return autorRepository.findByNome(nome);
    }

    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosNoAno(int ano) {
    return autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanOrAnoFalecimentoIsNull(ano, ano);
    }

}

