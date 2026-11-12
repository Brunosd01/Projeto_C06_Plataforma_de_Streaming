package Conteudos;

public class Filme extends Conteudo{
    private int duracaoMinutos;

    public Filme(String titulo, String genero, int anoLancamento, String diretor, int duracaoMinutos) {
        super(titulo, genero, anoLancamento, diretor);
        this.duracaoMinutos = duracaoMinutos;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}
