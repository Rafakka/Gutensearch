package com.logos.gutensearch.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    gutendexService.buscarLivros(titulo);
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

    public List<String> listarIdiomasLivros() {
        return livroRepository.findDistinctIdiomas();
    }

    public int contarLivros() {
        return (int) livroRepository.count();
    }

    public double mediaDownloadLivros() {
        return livroRepository.mediaDownloadLivros();
    }

    public Autor autorComMaisLivros() {
    List<Autor> autores = autorRepository.autorComMaisLivros(PageRequest.of(0, 1));
    return autores.isEmpty() ? null : autores.get(0);
    }

    public Map<String, Integer> livrosPorIdioma() {
        List<Object[]> resultados = livroRepository.livrosPorIdioma();
        return resultados.stream()
            .collect(Collectors.toMap(
                r -> (String) r[0],
                r -> ((Long) r[1]).intValue()
            ));
    }

    public List<Livro> top10Livros() {
        return livroRepository.findTop10ByOrderByDownloadsDesc();
    }
}
