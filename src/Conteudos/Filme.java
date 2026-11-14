package Conteudos;

public class Filme extends Conteudo{
    private int duracaoMinutos;

    public Filme(String titulo, String genero, int anoLancamento, String diretor, int duracaoMinutos) {
        super(titulo, genero, anoLancamento, diretor);
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("\n===================================");
        System.out.println("🎬 FILME: " + this.titulo.toUpperCase());
        System.out.println("===================================");
        System.out.println("ID: " + this.id);
        System.out.println("Gênero: " + this.genero);
        System.out.println("Ano de Lançamento: " + this.anoLancamento);
        System.out.println("Diretor: " + this.diretor);
        System.out.println("Duração Total: " + this.duracaoMinutos + " minutos");
        System.out.println("Nota Média: " + (this.totalAvaliacoes > 0 ? String.format("%.1f", this.notaMedia) : "Sem avaliações"));
        System.out.println("===================================");
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}
