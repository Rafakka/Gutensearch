package com.logos.gutensearch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logos.gutensearch.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
}