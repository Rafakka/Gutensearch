package com.logos.gutensearch.ui.command;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.logos.gutensearch.model.Livro;
import com.logos.gutensearch.services.Literatura;

@Component
public class BuscarLivro implements MenuCommand {
    
    private final Literatura literatura;
    private final Scanner scanner;

    public BuscarLivro(Literatura literatura, Scanner scanner) {
        this.literatura = literatura;
        this.scanner = scanner;
    }

    @Override
    public void executar() {
        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();
        List<Livro>livros = literatura.buscarELancarLivro(titulo);

        if (livros.isEmpty()) {
            System.out.println("Livro não encontrado");
        } else {
            livros.forEach(livro -> System.out.println(livro.getTitulo() + "-" + livro.getAutor().getNome()));
        }
    }

    @Override
    public String getNome() {
        return "Buscar livro por título";
    }
}
