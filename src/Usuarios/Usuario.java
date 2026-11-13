package Usuarios;

import Conteudos.Catalogo;

public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String senha;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // a IA que fez, coloquei so pra fazer o código rodar
    public boolean autenticar(String senhaDigitada) {
        // Compara a senha salva no objeto com a senha que o usuário digitou no login
        return this.senha.equals(senhaDigitada);
    }

    public abstract void exibirMenu(Catalogo catalogo);

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
