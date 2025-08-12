package com.logos.gutensearch.repository;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import com.logos.gutensearch.model.Livro;

public class LivroSpecification {
    
    public static Specification<Livro> filtroDinamico(String titulo, String idioma, String genero) {
        return (root, query, cb) -> {
            Predicate predicado = cb.conjunction();

            if(titulo !=null && !titulo.isBlank()){
                predicado = cb.and(predicado, cb.like(cb.lower(root.get("titulo")),"%" + titulo.toLowerCase() + "%"));
            }
            if(idioma !=null && !idioma.isBlank()){
                predicado = cb.and(predicado, cb.like(cb.lower(root.get("idioma")),"%" + idioma.toLowerCase() + "%"));
            }
            if(genero !=null && !genero.isBlank()){
                predicado = cb.and(predicado, cb.like(cb.lower(root.get("genero")),"%" + genero.toLowerCase() + "%"));
            }
            if (genero != null && !genero.isBlank()) {
                predicado = cb.and(predicado, cb.like(cb.lower(root.get("genero")),"%" + genero.toLowerCase() + "%"));
            } 

            return predicado;
        };
    }
}
