package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Conteudo;
import Conteudos.Filme;
import Conteudos.Serie;
import Exception.ConteudoNaoEncontradoException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrador extends Usuario {

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void cadastrarFilme(Catalogo catalogo, Scanner scanner) {
        try {
            System.out.println("\n--- CADASTRAR NOVO FILME ---");
            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            System.out.print("Ano de Lançamento: ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Diretor: ");
            String diretor = scanner.nextLine();

            System.out.print("Duração (em minutos): ");
            int duracao = scanner.nextInt();
            scanner.nextLine();

            Filme novoFilme = new Filme(titulo, genero, ano, diretor, duracao);
            catalogo.adicionar(novoFilme);
            System.out.println("Filme cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpar entrada inválida do buffer
            System.out.println("Erro: Entrada inválida!.");
        }
    }

    public void cadastrarSerieComEpisodiosCustomizados(Catalogo catalogo, Scanner scanner) {
        try {
            System.out.println("\n--- CADASTRAR NOVA SÉRIE ---");
            System.out.print("Título da Série: ");
            String titulo = scanner.nextLine();

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            System.out.print("Ano de Lançamento: ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Diretor: ");
            String diretor = scanner.nextLine();

            System.out.print("Quantidade de Temporadas: ");
            int temporadas = scanner.nextInt();

            System.out.print("Quantidade de Episódios a cadastrar: ");
            int totalEpisodios = scanner.nextInt();
            scanner.nextLine();

            Serie novaSerie = new Serie(titulo, genero, ano, diretor, temporadas, 0, 0);

            for (int i = 1; i <= totalEpisodios; i++) {
                System.out.println("\n-> Episódio " + i + ":");
                System.out.print("  Título do Episódio: ");
                String tituloEp = scanner.nextLine();

                System.out.print("  Duração (minutos): ");
                int duracaoEp = scanner.nextInt();
                scanner.nextLine();

                novaSerie.adicionarEpisodio(tituloEp, duracaoEp);
            }

            catalogo.adicionar(novaSerie);
            System.out.println("Série cadastrada com sucesso!");
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpar entrada inválida do buffer
            System.out.println("Erro: Entrada inválida!");
        }
    }

    public void removerConteudo(Catalogo catalogo, Scanner scanner) {
        System.out.print("Digite o ID do conteúdo a ser removido: ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();

            catalogo.remover(id);
            System.out.println("Conteúdo removido com sucesso!");
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpar entrada inválida do buffer
            System.out.println("Erro: O ID deve ser um número inteiro.");
        } catch (ConteudoNaoEncontradoException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    public void verDetalhesConteudo(Catalogo catalogo, Scanner scanner) {
        System.out.print("Digite o ID do conteúdo que deseja visualizar os detalhes: ");
        try {
            int id = scanner.nextInt();
            scanner.nextLine();

            Conteudo encontrado = catalogo.buscarPorId(id);
            encontrado.exibirDetalhes();
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpar entrada inválida do buffer
            System.out.println("Erro: O ID deve ser um número inteiro.");
        } catch (ConteudoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    @Override
    public void exibirMenu(Catalogo catalogo, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== PAINEL DO ADMINISTRADOR ===");
            System.out.println("Olá, " + this.nome);
            System.out.println("1. Cadastrar Novo Conteúdo (Filme/Série)");
            System.out.println("2. Remover Conteúdo por ID");
            System.out.println("3. Listar Catálogo Atual");
            System.out.println("4. Ver Detalhes de um Conteúdo");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer
            } catch (InputMismatchException e) {
                scanner.nextLine(); // Limpar entrada inválida do buffer
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    try {
                        int tipoConteudo;
                        do {
                            System.out.println("\nDigite 1 para cadastrar um Filme");
                            System.out.println("Digite 2 para cadastrar uma Série");
                            System.out.print("Opção: ");
                            tipoConteudo = scanner.nextInt();
                            scanner.nextLine();

                            if (tipoConteudo == 1) {
                                cadastrarFilme(catalogo, scanner);
                            } else if (tipoConteudo == 2) {
                                cadastrarSerieComEpisodiosCustomizados(catalogo, scanner);
                            } else {
                                System.out.println("Opção inválida! Digite 1 ou 2.");
                            }
                        } while (tipoConteudo != 1 && tipoConteudo != 2);
                    } catch (InputMismatchException e) {
                        scanner.nextLine(); // Limpar entrada inválida do buffer
                        System.out.println("Erro: Opção inválida! Digite apenas números.");
                    }
                    break;

                case 2:
                    removerConteudo(catalogo, scanner);
                    break;

                case 3:
                    System.out.println("\n--- CATÁLOGO ATUAL ---");
                    if (catalogo.listarTodos().isEmpty()) {
                        System.out.println("Nenhum conteúdo cadastrado.");
                    } else {
                        for (Conteudo c : catalogo.listarTodos()) {
                            System.out.println("ID: " + c.getId() + " | " + c.getTitulo() + " (" + c.getAnoLancamento() + ")");
                        }
                    }

                    // Exibição exclusiva da média de avaliações do catálogo para o Administrador
                    System.out.println("-----------------------------------");
                    if (catalogo.getTotalAvaliacoes() > 0) {
                        System.out.printf("Média de Avaliação do Catálogo: %.1f estrelas (%d avaliações)\n",
                                catalogo.getNotaMedia(), catalogo.getTotalAvaliacoes());
                    } else {
                        System.out.println("Média de Avaliação do Catálogo: Sem avaliações registradas.");
                    }
                    System.out.println("-----------------------------------");
                    break;

                case 4:
                    verDetalhesConteudo(catalogo, scanner);
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