package Usuarios;

import Conteudos.Catalogo;
import Conteudos.Conteudo;

import java.util.List;
import java.util.ArrayList;

public class Assinante extends Usuario {

    private List<Conteudo> minhaLista;
    private List<Conteudo> historico;

    //Construtor
    public Assinante(String nome, String email, String senha) {
        super(nome, email, senha);

        this.minhaLista = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    //Metodos da classe Assinante
    public void adicionarNaLista (Conteudo conteudo){
        minhaLista.add(conteudo);
        System.out.printf("Filme adicionado na lista");
    }

    public void adicionarAoHistorico(Conteudo conteudo){}

    public void assistir(Conteudo conteudo){}

    public void exibirMenu(){}

    //Metodo da classe mae
    @Override
    public void exibirMenu(Catalogo catalogo) {

    }
}
