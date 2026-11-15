package Conteudos;

public class Episodio {
    private String tituloEpisodio;
    private int duracaoMinutos;

    public Episodio(String tituloEpisodio, int duracaoMinutos) {
        this.tituloEpisodio = tituloEpisodio;
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getTituloEpisodio() {
        return tituloEpisodio;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }
}
