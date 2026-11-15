package Conteudos;

public abstract class Conteudo implements Avaliavel {

    private static int contadorId = 1;

    protected int id;
    protected String titulo;
    protected String genero;
    protected int anoLancamento;
    protected String diretor;
    protected double notaMedia;
    protected int totalAvaliacoes;

    public Conteudo(String titulo, String genero, int anoLancamento, String diretor) {
        this.id = contadorId++; // Atribui o ID automático a cada novo conteúdo
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.diretor = diretor;
        this.notaMedia = 0.0;
        this.totalAvaliacoes = 0;
    }

    @Override
    public void avaliar(double nota) {
        if (nota >= 0 && nota <= 5) {
            this.notaMedia = ((this.notaMedia * this.totalAvaliacoes) + nota) / (this.totalAvaliacoes + 1);
            this.totalAvaliacoes++;
        } else {
            System.out.println("Nota inválida! Escolha um valor entre 0 e 5.");
        }
    }

    // --- GETTERS E SETTERS ---

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public String getDiretor() {
        return diretor;
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

    public abstract void exibirDetalhes();

    public abstract void reproduzir();
}