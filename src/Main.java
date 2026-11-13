import Service.PlataformaStreaming;
import Usuarios.Assinante;
import Usuarios.Usuario;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlataformaStreaming plataforma = new PlataformaStreaming();

        plataforma.iniciar();

        int opcao;
        do {
            System.out.println("\n=== PLATAFORMA DE STREAMING ===");
            System.out.println("1. Cadastrar novo Usuário");
            System.out.println("2. Fazer Login");
            System.out.println("0. Sair e Salvar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO CADASTRO ---");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    Usuario novoAssinante = new Assinante(nome, email, senha);
                    plataforma.cadastrarUsuario(novoAssinante);

                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Senha: ");
                    String loginSenha = scanner.nextLine();

                    Usuario usuarioLogado = plataforma.login(loginEmail, loginSenha);

                    if (usuarioLogado != null) {
                        System.out.println("Login realizado com sucesso! Bem-vindo(a).");
                    } else {
                        System.out.println("Erro: Email ou senha incorretos!");
                    }
                    break;

                case 0:
                    plataforma.encerrar();
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}