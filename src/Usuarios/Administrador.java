package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Filme;
import Conteudos.Conteudo;
import java.util.Scanner;

public class Administrador extends Usuario {

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    // Método focado em instanciar e salvar no catálogo
    public void cadastrarConteudo(Catalogo catalogo, Scanner scanner) {
        System.out.println("\n--- CADASTRAR NOVO FILME ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("Ano de Lançamento: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // limpar buffer do teclado

        System.out.print("Diretor: ");
        String diretor = scanner.nextLine();

        System.out.print("Duração (em minutos): ");
        int duracao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer do teclado

        // Instancia o filme com os dados informados
        Filme novoFilme = new Filme(titulo, genero, ano, diretor, duracao);

        // Salva na memória do sistema (no HashMap do Catalogo)
        catalogo.adicionar(novoFilme);
    }

    // O Menu interativo do Administrador
    @Override
    public void exibirMenu(Catalogo catalogo, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== PAINEL DO ADMINISTRADOR ===");
            System.out.println("Olá, " + this.nome);
            System.out.println("1. Cadastrar Novo Filme");
            System.out.println("2. Listar Catálogo Atual");
            System.out.println("0. Fazer Logout");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarConteudo(catalogo, scanner);
                    break;
                case 2:
                    System.out.println("\n--- CATÁLOGO ---");
                    for (Conteudo c : catalogo.listarTodos()) {
                        System.out.println("ID: " + c.getId() + " | " + c.getTitulo() + " (" + c.getAnoLancamento() + ")");
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