package com.logos.gutensearch.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    private String nacionalidade;
    private String biografia;
    private String generos;
    private String dataNascimento;
    private String dataFalecimento;

    @ElementCollection
    private List<String> obras;

}