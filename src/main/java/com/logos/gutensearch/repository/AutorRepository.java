package com.logos.gutensearch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.logos.gutensearch.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    Optional<Autor> findByNome(String nome);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanOrAnoFalecimentoIsNull(int ano1, int ano2);

    @Query("SELECT l.autor FROM Livro l GROUP BY l.autor ORDER BY COUNT(l) DESC")
    List<Autor> autorComMaisLivros(Pageable pageable);
}
