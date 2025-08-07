package com.logos.gutensearch.ui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.logos.gutensearch.services.Literatura;

@Component
public class Principal {
    @Autowired
    private Literatura service;
    
    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \n
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros por idioma
                    6 - Gerar estatísticas
                    7 - Top 10 livros
                    0 - Sair
                    """;
            
            System.out.println(menu);
            Scanner scanner = new Scanner(System.in);
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    var titulo = scanner.nextLine();
                    service.buscarELancarLivro(titulo);
                    break;
                case 2:
                    // Listar livros
                    break;
                // outros casos
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}