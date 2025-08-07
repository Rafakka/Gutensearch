package com.logos.gutensearch.ui.command;

import java.util.List;
import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.services.Literatura;

public class ListarLivrosReg implements MenuCommand {

    private final Literatura literatura;

    public ListarLivrosReg(Literatura literatura) {
        this.literatura = literatura;
    }

    @Override
    public void executar() {
    List<Livro> livros = literatura.listarLivrosReg();

        if (livros.isEmpty()) {
            System.out.println("Nenhum Livro registrado.");
        } else {
            System.out.println("Livros registrados:");
            livros.forEach(livro -> {
                System.out.printf("Titulo: %s, Autor: %s%n", livro.getTitulo(), livro.getAutor().getNome());
                System.out.printf("ID: %d, ISBN: %s%n", livro.getId(), livro.getIsbn() != null ? livro.getIsbn() : "N/A");
                System.out.printf("Data de publicação: %s%n", livro.getDataPublicacao() != null ? livro.getDataPublicacao() : "N/A");
                System.out.printf("Idioma: %s%n", livro.getIdioma() != null ? livro.getIdioma() : "N/A");
            });
        }
    }

    @Override
    public String getNome() {
        return "Listar livros registrados";
    }
    
}
