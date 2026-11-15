package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Conteudo;
import Exception.ConteudoNaoEncontradoException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Assinante extends Usuario {
    private List<Conteudo> minhaLista;
    private List<Conteudo> historico;

    public Assinante(String nome, String email, String senha) {
        super(nome, email, senha);
        this.minhaLista = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    @Override
    public void exibirMenu(Catalogo catalogo, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== PAINEL DO ASSINANTE ===");
            System.out.println("Olá, " + this.nome);
            System.out.println("1. Ver Catálogo de Filmes e Séries");
            System.out.println("2. Ver Minha Lista");
            System.out.println("3. Adicionar Conteúdo à Minha Lista");
            System.out.println("4. Ver Detalhes de um Conteúdo");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CATÁLOGO ---");
                    if (catalogo.listarTodos().isEmpty()) {
                        System.out.println("O catálogo está vazio no momento.");
                    } else {
                        for (Conteudo c : catalogo.listarTodos()) {
                            System.out.println("ID: " + c.getId() + " | " + c.getTitulo() + " (" + c.getAnoLancamento() + ")");
                        }

                        // Opção para o assinante avaliar o catálogo
                        System.out.println("\nDeseja avaliar o catálogo da plataforma?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                        System.out.print("Escolha uma opção: ");
                        int opAvaliar = scanner.nextInt();
                        scanner.nextLine();

                        if (opAvaliar == 1) {
                            System.out.print("Digite uma nota de 0 a 5 para o catálogo: ");
                            double notaCat = scanner.nextDouble();
                            scanner.nextLine();
                            catalogo.avaliar(notaCat);
                        }
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

                case 3:
                    System.out.print("Digite o ID do conteúdo que deseja adicionar à sua lista: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Conteudo encontrado = catalogo.buscarPorId(id);
                        adicionarNaLista(encontrado);
                    } catch (ConteudoNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do conteúdo que deseja ver os detalhes: ");
                    int idDetalhe = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        Conteudo encontrado = catalogo.buscarPorId(idDetalhe);
                        encontrado.exibirDetalhes(); // Exibe informações e a média de avaliação do conteúdo

                        // Opção para o assinante avaliar este conteúdo
                        System.out.println("\nDeseja avaliar este conteúdo?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                        System.out.print("Escolha uma opção: ");
                        int opAvaliarConteudo = scanner.nextInt();
                        scanner.nextLine();

                        if (opAvaliarConteudo == 1) {
                            System.out.print("Digite uma nota de 0 a 5 para este conteúdo: ");
                            double notaCont = scanner.nextDouble();
                            scanner.nextLine();
                            encontrado.avaliar(notaCont);
                            System.out.println("Avaliação do conteúdo registrada com sucesso!");
                        }
                    } catch (ConteudoNaoEncontradoException e) {
                        System.out.println("Erro: " + e.getMessage());
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

    public void adicionarNaLista(Conteudo conteudo) {
        if (conteudo != null && !minhaLista.contains(conteudo)) {
            minhaLista.add(conteudo);
            System.out.println("Conteúdo adicionado com sucesso à sua lista!");
        } else if (minhaLista.contains(conteudo)) {
            System.out.println("Este conteúdo já está na sua lista.");
        }
    }

    public void adicionarAoHistorico(Conteudo conteudo) {}

    public void assistir(Conteudo conteudo) {}

    public List<Conteudo> getMinhaLista() {
        return minhaLista;
    }
}