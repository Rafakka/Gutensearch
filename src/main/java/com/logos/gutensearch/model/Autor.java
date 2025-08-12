package com.logos.gutensearch.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer dataNascimento;
    private Integer dataFalecimento;

    @ElementCollection
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Livro> obras;
    public Autor(String nome, Integer anoNascimento, Integer anoFalecimento) {
    this.nome = nome;
    this.anoNascimento = anoNascimento;
    this.anoFalecimento = anoFalecimento;
}

}