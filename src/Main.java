import Exception.CadastroInvalidoException;
import Exception.ConteudoNaoEncontradoException;
import Exception.LoginInvalidoException;
import Service.PlataformaStreaming;
import Usuarios.Assinante;
import Usuarios.Usuario;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlataformaStreaming plataforma = new PlataformaStreaming();

        // Carrega dados salvos ao iniciar a plataforma
        plataforma.iniciar();

        int opcao;
        do {
            System.out.println("\n=== PLATAFORMA DE STREAMING ===");
            System.out.println("1. Cadastrar novo Usuário");
            System.out.println("2. Fazer Login");
            System.out.println("0. Sair e Salvar");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO CADASTRO ---");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    try {
                        Usuario novoAssinante = new Assinante(nome, email, senha);
                        plataforma.cadastrarUsuario(novoAssinante);
                        System.out.println("Usuário cadastrado com sucesso!");
                    } catch (CadastroInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Senha: ");
                    String loginSenha = scanner.nextLine();

                    // Tratamento da exceção verificada LoginInvalidoException
                    try {
                        Usuario usuarioLogado = plataforma.login(loginEmail, loginSenha);
                        System.out.println("Login realizado com sucesso! Bem-vindo(a), " + usuarioLogado.getNome() + ".");
                        // Executa o menu específico via Polimorfismo
                        usuarioLogado.exibirMenu(plataforma.getCatalogo(), scanner);
                    } catch (LoginInvalidoException e) {
                        System.out.println("Erro no login: " + e.getMessage());
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