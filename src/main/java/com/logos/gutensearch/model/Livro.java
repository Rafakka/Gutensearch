
package com.logos.gutensearch.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;

    private Integer downloads;

    private String dataPublicacao;

    private String isbn;

    private String genero;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;
}