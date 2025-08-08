
package com.logos.gutensearch.ui.command;

import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.logos.gutensearch.model.Autor;
import com.logos.gutensearch.services.Literatura;

@Component
public class ListarAutoresVivos implements MenuCommand {

    private final Scanner scanner;
    private final Literatura literatura;

    public ListarAutoresVivos(Literatura literatura, Scanner scanner) {
    this.scanner = scanner;
    this.literatura = literatura;
    }

    @Override
    public void executar() {
        System.out.println("Digite o ano para listar autores vivos:");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha
        List<Autor> autoresVivos = literatura.listarAutoresVivosNoAno(ano);
        
        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado no ano " + ano);
        } else {
            System.out.println("Autores vivos no ano " + ano + ":");
            autoresVivos.forEach(autor -> System.out.println(autor.getNome()));
        }
        System.out.println("Listagem concluída.");
        
    }

    @Override
    public String getNome() {
        return "Listar autores vivos";
    }
    
}