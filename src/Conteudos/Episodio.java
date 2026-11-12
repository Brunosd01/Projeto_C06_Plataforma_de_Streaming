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

    public void setTituloEpisodio(String tituloEpisodio) {
        this.tituloEpisodio = tituloEpisodio;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}
