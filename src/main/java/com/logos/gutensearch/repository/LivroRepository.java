package com.logos.gutensearch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logos.gutensearch.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);
    List<Livro> findByIdioma(String idioma);
    Object estatisticas();
}