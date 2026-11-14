package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Conteudo;
import Conteudos.Filme;
import Conteudos.Serie;
import java.util.Scanner;

public class Administrador extends Usuario {

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // Método para cadastrar Filme
    public void cadastrarFilme(Catalogo catalogo, Scanner scanner) {
        System.out.println("\n--- CADASTRAR NOVO FILME ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("Ano de Lançamento: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer do teclado

        System.out.print("Diretor: ");
        String diretor = scanner.nextLine();

        System.out.print("Duração (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer do teclado

        Filme novoFilme = new Filme(titulo, genero, ano, diretor, duracao);
        catalogo.adicionar(novoFilme);
        System.out.println("Filme cadastrado com sucesso!");
    }

    // Método para cadastrar Série exclusivamente de forma manual
    public void cadastrarSerie(Catalogo catalogo, Scanner scanner) {
        System.out.println("\n--- CADASTRAR NOVA SÉRIE ---");
        System.out.print("Título da Série: ");
        String titulo = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("Ano de Lançamento: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer do teclado

        System.out.print("Diretor: ");
        String diretor = scanner.nextLine();

        System.out.print("Quantidade de Temporadas: ");
        int temporadas = scanner.nextInt();

        System.out.print("Quantidade Total de Episódios a cadastrar: ");
        int totalEpisodios = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer do teclado

        // Instancia a série sem episódios pré-gerados
        Serie novaSerie = new Serie(titulo, genero, ano, diretor, temporadas, 0, 0);

        // Solicita o título e a duração de cada episódio individualmente
        for (int i = 1; i <= totalEpisodios; i++) {
            System.out.println("\n-> Episódio " + i + ":");
            System.out.print("  Título do Episódio: ");
            String tituloEp = scanner.nextLine();

            System.out.print("  Duração (em minutos): ");
            int duracaoEp = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do teclado

            // Adiciona o episódio à série garantindo a Composição
            novaSerie.adicionarEpisodio(tituloEp, duracaoEp);
        }

        catalogo.adicionar(novaSerie);
        System.out.println("Série cadastrada com sucesso!");
    }

    // Método para remover conteúdo por ID
    public void removerConteudo(Catalogo catalogo, Scanner scanner) {
        System.out.println("\n--- REMOVER CONTEÚDO ---");
        System.out.print("Digite o ID do conteúdo que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer do teclado

        catalogo.remover(id);
    }

    @Override
    public void exibirMenu(Catalogo catalogo, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== PAINEL DO ADMINISTRADOR ===");
            System.out.println("Olá, " + this.nome);
            System.out.println("1. Cadastrar Novo Conteudo");
            System.out.println("2. Remover Um Conteudo");
            System.out.println("3. Listar Catálogo Atual");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do teclado

            switch (opcao) {
                case 1:
                    int tipoConteudo;
                    do {
                        System.out.println("\nDigite 1 para cadastrar um Filme");
                        System.out.println("Digite 2 para cadastrar uma Série");
                        System.out.print("Opção: ");
                        tipoConteudo = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer do teclado

                        if (tipoConteudo == 1) {
                            cadastrarFilme(catalogo, scanner);
                        } else if (tipoConteudo == 2) {
                            cadastrarSerie(catalogo, scanner);
                        } else {
                            System.out.println("Opção inválida! Digite 1 ou 2.");
                        }
                    } while (tipoConteudo != 1 && tipoConteudo != 2);
                    break;

                case 2:
                    removerConteudo(catalogo, scanner);
                    break;

                case 3:
                    System.out.println("\n--- CATÁLOGO ATUAL ---");
                    if (catalogo.listarTodos().isEmpty()) {
                        System.out.println("O catálogo está vazio.");
                    } else {
                        for (Conteudo c : catalogo.listarTodos()) {
                            System.out.println("ID: " + c.getId() + " | " + c.getTitulo() + " (" + c.getAnoLancamento() + ")");
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
}