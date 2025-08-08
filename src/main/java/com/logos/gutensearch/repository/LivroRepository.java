package com.logos.gutensearch.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logos.gutensearch.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

    List<Livro> findByIdioma(String idioma);

    Object estatisticas();

    @Query("SELECT DISTINCT l.idioma FROM Livro l")
    List<String> findDistinctIdiomas();

    @Query("SELECT l FROM Livro l WHERE l.autor.id = ?1")
    List<Livro> findByAutorId(Long autorId);

    @Query("SELECT l FROM Livro l WHERE l.genero = ?1")
    List<Livro> findByGenero(String genero);

    @Query("SELECT l.idioma, COUNT(l) FROM Livro l GROUP BY l.idioma")
    List<Object[]> livrosPorIdioma();

    @Query("SELECT AVG(l.downloads) FROM Livro l")
    Double mediaDownloadLivros();
    
    @Query("SELECT l FROM Livro l ORDER BY l.downloads DESC")
    List<Livro> top10Livros(Pageable pageable);
    
}