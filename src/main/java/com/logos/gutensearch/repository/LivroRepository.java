package com.logos.gutensearch.repository;

import com.logos.gutensearch.model.Livro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor<Livro> {

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

    Optional<Livro> findFirstByTituloContainingIgnoreCase(String titulo);

    List<Livro> findByTituloContainingIgnoreCaseAndIdiomaContainingIgnoreCaseAndGeneroContainingIgnoreCase(
    String titulo, String idioma, String genero);

}
