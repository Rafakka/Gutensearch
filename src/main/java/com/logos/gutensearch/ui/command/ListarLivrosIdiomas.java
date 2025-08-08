package com.logos.gutensearch.ui.command;

import org.springframework.stereotype.Component;

import com.logos.gutensearch.services.Literatura;

@Component
public class ListarLivrosIdiomas implements MenuCommand {
    private final Literatura literatura;

    public ListarLivrosIdiomas(Literatura literatura) {
        this.literatura = literatura;
    }

    @Override
    public void executar() {
        var idiomas = literatura.listarIdiomasLivros();
        
        if (idiomas.isEmpty()) {
            System.out.println("Nenhum idioma de livro registrado.");
        } else {
            System.out.println("Idiomas dos livros registrados:");
            idiomas.forEach(idioma -> System.out.println("- " + idioma));
        }
    }

    @Override
    public String getNome() {
        return "Listar idiomas dos livros";
    }
}