package Usuarios;

public class Assinante extends Usuario {

    public Assinante(String nome, String email, String senha) {
        // Repassa os dados para o construtor da classe mãe (Usuario)
        super(nome, email, senha);
    }
}