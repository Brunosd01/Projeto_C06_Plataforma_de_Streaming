package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Conteudo;
import Exception.ConteudoNaoEncontradoException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Assinante extends Usuario {

    public Assinante(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public void exibirMenu(Catalogo catalogo, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== PAINEL DO ASSINANTE ===");
            System.out.println("Olá, " + this.nome);
            System.out.println("1. Ver Catálogo Completo");
            System.out.println("2. Ver Detalhes de uma Mídia (Filme/Série)");
            System.out.println("3. Ver Minha Lista");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e){
                scanner.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CATÁLOGO ---");
                    for (Conteudo c : catalogo.listarTodos()) {
                        System.out.println("ID: " + c.getId() + " | " + c.getTitulo() + " (" + c.getAnoLancamento() + ")");
                    }
                    break;

                case 2:
                    System.out.print("\nDigite o ID da mídia que deseja ver os detalhes: ");

                    try {
                        int idBusca = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer

                        Conteudo midia = catalogo.buscarPorId(idBusca);
                        midia.exibirDetalhes();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        System.out.println("Erro: ID incorreta.");
                    } catch (ConteudoNaoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Fazendo logout...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}