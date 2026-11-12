package Conteudos;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Conteudo{
    private int temporadas;
    private List<Episodio> episodios;

    public Serie(String titulo, String genero, int anoLancamento, String diretor, int temporadas, int quantidadeEpisodios, int duracaoMinutosEpisodio) {
        super(titulo, genero, anoLancamento, diretor);

        this.temporadas = temporadas;
        this.episodios = new ArrayList<>();

        for (int i = 1; i <= quantidadeEpisodios; i++) {
            String tituloEpisodio = "Episódio " + i;
            this.episodios.add(new Episodio(tituloEpisodio, duracaoMinutosEpisodio));
        }

    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }
}
