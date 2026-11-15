package Conteudos;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Conteudo {
    private int temporadas;
    private List<Episodio> episodios;

    public Serie(String titulo, String genero, int anoLancamento, String diretor, int temporadas, int quantidadeEpisodiosPorTemporada, int duracaoMinutosEpisodio) {
        super(titulo, genero, anoLancamento, diretor);

        this.temporadas = temporadas;
        this.episodios = new ArrayList<>();


        int totalEpisodios = temporadas * quantidadeEpisodiosPorTemporada;
        for (int i = 1; i <= totalEpisodios; i++) {
            String tituloEpisodio = "Episódio " + i;
            this.episodios.add(new Episodio(tituloEpisodio, duracaoMinutosEpisodio));
        }
    }

    public void adicionarEpisodio(String tituloEpisodio, int duracaoMinutos) {
        this.episodios.add(new Episodio(tituloEpisodio, duracaoMinutos));
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("\n===================================");
        System.out.println("📺 SÉRIE: " + this.titulo.toUpperCase());
        System.out.println("===================================");
        System.out.println("ID: " + this.id);
        System.out.println("Gênero: " + this.genero);
        System.out.println("Ano de Lançamento: " + this.anoLancamento);
        System.out.println("Diretor: " + this.diretor);
        System.out.println("Temporadas: " + this.temporadas);
        System.out.println("Nota Média: " + (this.totalAvaliacoes > 0 ? String.format("%.1f", this.notaMedia) : "Sem avaliações"));
        System.out.println("-----------------------------------");
        System.out.println("EPISÓDIOS CADASTRADOS (" + this.episodios.size() + "):");

        if (this.episodios.isEmpty()) {
            System.out.println("Nenhum episódio cadastrado nesta série.");
        } else {
            for (int i = 0; i < this.episodios.size(); i++) {
                Episodio ep = this.episodios.get(i);
                System.out.println("  [" + (i + 1) + "] " + ep.getTituloEpisodio() + " (" + ep.getDuracaoMinutos() + " min)");
            }
        }
        System.out.println("===================================");
    }

    @Override
    public void reproduzir() {
        System.out.println("\n📺 Iniciando a série '" + this.titulo + "':");
        if (this.episodios.isEmpty()) {
            System.out.println(" Nenhum episódio disponível para reprodução.");
        } else {
            for (int i = 0; i < this.episodios.size(); i++) {
                Episodio ep = this.episodios.get(i);
                System.out.println(" Reproduzindo Episódio " + (i + 1) + ": " + ep.getTituloEpisodio() + " (" + ep.getDuracaoMinutos() + " min)");
            }
        }
    }

    public int getTemporadas() {
        return temporadas;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }
}