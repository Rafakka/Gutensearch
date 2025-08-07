package com.logos.gutensearch.ui.command;

import java.util.List;

import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.services.Literatura;

public class ListarAutoresReg implements MenuCommand {
    private final Literatura literatura;

    public ListarAutoresReg(Literatura literatura) {
        this.literatura = literatura;
    }

    @Override
    public void executar() {
            List<Autor> autores = literatura.ListarAutoresRegistrados();

        if (autores.isEmpty()) {
            System.out.println("Nenhum Autor registrado.");
        } else {
            System.out.println("Autores registrados:");
            autores.forEach(autor-> {
                System.out.printf("Autor: %s%n", autor.getNome());
                System.out.printf("ID: %d%n", autor.getId());
                System.out.printf("Nacionalidade: %s%n", autor.getNacionalidade() != null ? autor.getNacionalidade() : "N/A");
                System.out.printf("Data de nascimento: %s%n", autor.getDataNascimento() != null ? autor.getDataNascimento() : "N/A");
                System.out.printf("Data de falecimento: %s%n", autor.getDataFalecimento() != null ? autor.getDataFalecimento() : "N/A");
                System.out.printf("Obras: %s%n", autor.getObras() != null ? autor.getObras() : "N/A");
                System.out.printf("Biografia: %s%n", autor.getBiografia() != null ? autor.getBiografia() : "N/A");
                System.out.printf("Gêneros: %s%n", autor.getGeneros() != null ? autor.getGeneros() : "N/A");
            });
        }
    }

    @Override
    public String getNome() {
        return "Listar autores registrados";
    }
    
}
