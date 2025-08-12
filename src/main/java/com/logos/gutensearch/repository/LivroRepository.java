package com.logos.gutensearch.repository;

import com.logos.gutensearch.model.Livro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @EntityGraph(attributePaths = "autor")
    @NonNull
    List<Livro> findAll();

    @EntityGraph(attributePaths = "autor")
    List<Livro> findTop10ByOrderByDownloadsDesc();

    @EntityGraph(attributePaths = "autor")
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT DISTINCT l.idioma FROM Livro l")
    List<String> findDistinctIdiomas();

    @Query("SELECT AVG(l.downloads) FROM Livro l")
    Double mediaDownloadLivros();

}
