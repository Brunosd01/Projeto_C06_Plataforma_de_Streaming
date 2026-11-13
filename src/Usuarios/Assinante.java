package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Conteudo;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Assinante extends Usuario {

    private List<Conteudo> minhaLista;
    private List<Conteudo> historico;

    // Construtor
    public Assinante(String nome, String email, String senha) {
        super(nome, email, senha);

        this.minhaLista = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    // Único exibirMenu correto, com a marcação @Override e recebendo o Scanner
    @Override
    public void exibirMenu(Catalogo catalogo, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== PAINEL DO ASSINANTE ===");
            System.out.println("Olá, " + this.nome);
            System.out.println("1. Ver Catálogo de Filmes");
            System.out.println("2. Ver Minha Lista");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer do teclado

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CATÁLOGO ---");
                    for (Conteudo c : catalogo.listarTodos()) {
                        System.out.println("ID: " + c.getId() + " | " + c.getTitulo() + " (" + c.getAnoLancamento() + ")");
                    }
                    break;
                case 2:
                    System.out.println("\n--- MINHA LISTA ---");
                    if (minhaLista.isEmpty()) {
                        System.out.println("Sua lista de favoritos está vazia.");
                    } else {
                        for (Conteudo c : minhaLista) {
                            System.out.println("- " + c.getTitulo());
                        }
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

    // Metodos da classe Assinante
    public void adicionarNaLista(Conteudo conteudo){
        minhaLista.add(conteudo);
        System.out.println("Filme adicionado na lista");
    }

    public void adicionarAoHistorico(Conteudo conteudo){}

    public void assistir(Conteudo conteudo){}

}