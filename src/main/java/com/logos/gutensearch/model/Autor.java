package com.logos.gutensearch.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autor")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Autor(String nome, Integer anoNascimento, Integer anoFalecimento) {
    this.nome = nome;
    this.anoNascimento = anoNascimento;
    this.anoFalecimento = anoFalecimento;
}

}