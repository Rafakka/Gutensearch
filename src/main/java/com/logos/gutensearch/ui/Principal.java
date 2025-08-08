package com.logos.gutensearch.ui;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.logos.gutensearch.services.Literatura;
import com.logos.gutensearch.ui.command.BuscarLivro;
import com.logos.gutensearch.ui.command.ListarAutoresReg;
import com.logos.gutensearch.ui.command.ListarAutoresVivos;
import com.logos.gutensearch.ui.command.ListarLivrosIdiomas;
import com.logos.gutensearch.ui.command.ListarLivrosReg;
import com.logos.gutensearch.ui.command.MenuCommand;


@Component
public class Principal{

    private final Literatura literatura;

    public Principal(Literatura literatura) {
        this.literatura = literatura;
    }
    Scanner scanner = new Scanner(System.in);

    public void exibeMenu() {
        Map<Integer, MenuCommand> comandos = new LinkedHashMap<>();
        comandos.put(1, new BuscarLivro(literatura, scanner));
        comandos.put(2, new ListarAutoresReg(literatura));
        comandos.put(3, new ListarAutoresVivos(literatura, scanner));
        comandos.put(4, new ListarLivrosIdiomas(literatura));
        comandos.put(5, new ListarLivrosReg(literatura));

    while (true) {
        System.out.println("\nMenu");
        for (var entry : comandos.entrySet()) {
            System.out.printf("%d - %s%n", entry.getKey(), entry.getValue().getNome());    
        }
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        if (opcao == 0) {
            System.out.println("Saindo...");
            break;
        }

    MenuCommand comando = comandos.get(opcao);

        if (comando != null){
            comando.executar();
        } else {
            System.out.println("Opção Invalida, tente novamente");
            }
        }
    }
}
