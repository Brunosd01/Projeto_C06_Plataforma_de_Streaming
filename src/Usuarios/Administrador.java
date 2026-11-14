package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Conteudo;
import Conteudos.Filme;
import Conteudos.Serie;
import Exception.CadastroInvalidoException;
import Exception.ConteudoNaoEncontradoException;
import java.util.InputMismatchException;
import java.util.Scanner;
import Service.GerenciadorArquivos;

public class Administrador extends Usuario {

    private GerenciadorArquivos arquivos = new GerenciadorArquivos();

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void cadastrarFilme(Catalogo catalogo, Scanner scanner) {
        System.out.println("\n--- CADASTRAR NOVO FILME ---");
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            System.out.print("Ano de Lançamento: ");
            String anoStr = scanner.nextLine();

            System.out.print("Diretor: ");
            String diretor = scanner.nextLine();

            System.out.print("Duração (em minutos): ");
            String duracaoStr = scanner.nextLine();

            if (titulo.isBlank() || genero.isBlank() || anoStr.isBlank() || diretor.isBlank() || duracaoStr.isBlank()) {
                throw new CadastroInvalidoException("Erro: Todos os campos do filme devem ser preenchidos.");
            }

            int ano = Integer.parseInt(anoStr.trim());
            int duracao = Integer.parseInt(duracaoStr.trim());

            if (ano <= 0 || duracao <= 0) {
                throw new CadastroInvalidoException("Erro: Valor de ano e duração errados.");
            }

            Filme novoFilme = new Filme(titulo, genero, ano, diretor, duracao);
            catalogo.adicionar(novoFilme);
            arquivos.salvarCatalogo(catalogo);
            System.out.println("Filme cadastrado com sucesso!");
        } catch (CadastroInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor de ano e duração errados.");
        }
    }

    public void cadastrarSerie(Catalogo catalogo, Scanner scanner) {
        System.out.println("\n--- CADASTRAR NOVA SÉRIE ---");
        try {
            System.out.print("Título da Série: ");
            String titulo = scanner.nextLine();

            System.out.print("Gênero: ");
            String genero = scanner.nextLine();

            System.out.print("Ano de Lançamento: ");
            String anoStr = scanner.nextLine();

            System.out.print("Diretor: ");
            String diretor = scanner.nextLine();

            System.out.print("Quantidade de Temporadas: ");
            String temporadasStr = scanner.nextLine();

            System.out.print("Quantidade Total de Episódios a cadastrar: ");
            String totalEpisodiosStr = scanner.nextLine();

            if (titulo.isBlank() || genero.isBlank() || anoStr.isBlank() || diretor.isBlank() || temporadasStr.isBlank() || totalEpisodiosStr.isBlank()) {
                throw new CadastroInvalidoException("Erro: Todos os campos da série devem ser preenchidos.");
            }

            int ano = Integer.parseInt(anoStr.trim());
            int temporadas = Integer.parseInt(temporadasStr.trim());
            int totalEpisodios = Integer.parseInt(totalEpisodiosStr.trim());

            if (ano <= 0 || temporadas <= 0 || totalEpisodios <= 0) {
                throw new CadastroInvalidoException("Erro: Valor de ano, temporada e duração errados.");
            }

            // Instancia a série sem episódios pré-gerados
            Serie novaSerie = new Serie(titulo, genero, ano, diretor, temporadas, 0, 0);

            // Solicita o título e a duração de cada episódio individualmente
            for (int i = 1; i <= totalEpisodios; i++) {
                System.out.println("\n-> Episódio " + i + ":");
                System.out.print("  Título do Episódio: ");
                String tituloEp = scanner.nextLine();

                System.out.print("  Duração (em minutos): ");
                String duracaoEpStr = scanner.nextLine();

                if (tituloEp.isBlank() || duracaoEpStr.isBlank()) {
                    throw new CadastroInvalidoException("Erro: Todos os campos do episódio devem ser preenchidos.");
                }

                int duracaoEp = Integer.parseInt(duracaoEpStr.trim());
                if (duracaoEp <= 0) {
                    throw new CadastroInvalidoException("Erro: Duração da temporada errado.");
                }

                novaSerie.adicionarEpisodio(tituloEp, duracaoEp);
            }

            catalogo.adicionar(novaSerie);
            arquivos.salvarCatalogo(catalogo);
            System.out.println("Série cadastrada com sucesso!");
        } catch (CadastroInvalidoException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Todos os campos devem ser preenchidos.");
        }
    }

    public void removerConteudo(Catalogo catalogo, Scanner scanner) {
        System.out.println("\n--- REMOVER CONTEÚDO ---");
        System.out.print("Digite o ID do conteúdo que deseja remover: ");

        try {
            int id = scanner.nextInt();
            scanner.nextLine();

            catalogo.remover(id);
            arquivos.salvarCatalogo(catalogo);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Erro: ID incorreta.");
        } catch (ConteudoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
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

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e){
                scanner.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    int tipoConteudo;
                    do {
                        System.out.println("\nDigite 1 para cadastrar um Filme");
                        System.out.println("Digite 2 para cadastrar uma Série");
                        System.out.print("Opção: ");

                        try {
                            tipoConteudo = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e){
                            scanner.nextLine();
                            tipoConteudo = -1;
                        }

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