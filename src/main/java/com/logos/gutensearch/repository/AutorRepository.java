package com.logos.gutensearch.repository;

import com.logos.gutensearch.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    List<Autor> findByAnoFalecimentoIsNullOrAnoFalecimentoAfter(int ano);

    List<Autor> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.obras")
    List<Autor> findAllAutoresComObras();

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.obras WHERE a.nome = :nome")
    Optional<Autor> findByNomeComObras(@Param("nome") String nome);

}

