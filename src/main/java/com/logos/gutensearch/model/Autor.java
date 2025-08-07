package com.logos.gutensearch.model;

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
    public Autor getAutor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutor'");
    }
    public Object getNacionalidade() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNacionalidade'");
    }
    public Object getDataNascimento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataNascimento'");
    }
    public Object getDataFalecimento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataFalecimento'");
    }
    public Object getObras() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObras'");
    }
    public Object getBiografia() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBiografia'");
    }
    public Object getGeneros() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGeneros'");
    }

}