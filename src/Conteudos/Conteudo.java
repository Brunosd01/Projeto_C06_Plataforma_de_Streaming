package Conteudos;

public abstract class Conteudo implements Avaliavel{
    protected int id;
    protected String titulo;
    protected String genero;
    protected int anoLancamento;
    protected String diretor;
    protected double notaMedia;
    protected int totalAvaliacoes;

    // 1. Criamos um contador estático que pertence à classe inteira
    private static int contadorId = 1;

    public Conteudo(String titulo, String genero, int anoLancamento, String diretor) {
        // 2. Toda vez que um Filme ou Série nascer, ele recebe o número atual e aumenta 1
        this.id = contadorId++;

        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.diretor = diretor;
    }

    public int getId() {
        return this.id; // Retorne a variável que guarda o ID
    }

    @Override
    public void avaliar(double nota){
        notaMedia = ((notaMedia * totalAvaliacoes) + nota) / (totalAvaliacoes + 1);
        totalAvaliacoes++;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }
}
