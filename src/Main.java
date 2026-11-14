import Conteudos.Conteudo;
import Service.PlataformaStreaming;
import Usuarios.Assinante;
import Usuarios.Usuario;
import java.util.Scanner;
import java.util.InputMismatchException;
import Exception.ConteudoNaoEncontradoException;
import Exception.LoginInvalidoException;
import Exception.CadastroInvalidoException;

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
            System.out.println("3. Ver Detalhes de um Conteúdo");
            System.out.println("0. Sair e Salvar");
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

                    try {
                        Usuario usuarioLogado = plataforma.login(loginEmail, loginSenha);
                        System.out.println("Login realizado com sucesso. Bem-vindo.");
                        usuarioLogado.exibirMenu(plataforma.getCatalogo(), scanner);
                    } catch (LoginInvalidoException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3: // BLOCO ADICIONADO PARA BUSCA DIRETA
                    System.out.print("\nDigite o ID do conteúdo que deseja visualizar: ");

                    try {
                        int idBusca = scanner.nextInt();
                        scanner.nextLine(); // Limpar buffer do teclado

                        Conteudo conteudo = plataforma.getCatalogo().buscarPorId(idBusca);
                        conteudo.exibirDetalhes();
                    } catch (InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Erro: ID incorreta.");
                    } catch (ConteudoNaoEncontradoException e){
                        System.out.println(e.getMessage());
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