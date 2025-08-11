package com.logos.gutensearch.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.logos.gutensearch.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT DISTINCT l.idioma FROM Livro l")
    List<String> findDistinctIdiomas();

    List<Livro> findByAutorId(Long autorId);

    List<Livro> findByGenero(String genero);

    @Query("SELECT l.idioma, COUNT(l) FROM Livro l GROUP BY l.idioma")
    List<Object[]> livrosPorIdioma();

    @Query("SELECT AVG(l.downloads) FROM Livro l")
    Double mediaDownloadLivros();
    
    List<Livro> findTop10ByOrderByDownloadsDesc();

    Optional<Livro> findByTituloAndAutorNome(String titulo, String nome);

}