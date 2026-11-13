package Usuarios;

public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public boolean autenticar(String senhaDigitada) {
        // Compara a senha salva no objeto com a senha que o usuário digitou no login
        return this.senha.equals(senhaDigitada);
    }

    public String getEmail() {
        return this.email;
    }
}
