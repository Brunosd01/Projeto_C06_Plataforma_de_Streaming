package Usuarios;

import Conteudos.Catalogo;
import java.util.Scanner;

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
        return this.senha.equals(senhaDigitada);
    }

    public abstract void exibirMenu(Catalogo catalogo, Scanner scanner);


    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public String getNome() {
        return nome;
    }
}
