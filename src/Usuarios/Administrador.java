package Usuarios;

import Conteudos.Catalogo;

public class Administrador extends Usuario {

    //Construtor
    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    //Metodos da classe Administrador
    public void cadastrarConteudo(Catalogo catalogo){}

    public void removerConteudo(Catalogo catalogo){}

    public void exibirMenu(){}


    //Metodo da classe mae
    @Override
    public void exibirMenu(Catalogo catalogo) {

    }
}
