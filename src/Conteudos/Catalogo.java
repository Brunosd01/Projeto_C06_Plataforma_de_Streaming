package Conteudos;

import Exception.ConteudoNaoEncontradoException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Catalogo implements Avaliavel {
    private Map<Integer, Conteudo> conteudos;
    private double notaMedia;
    private int totalAvaliacoes;

    public Catalogo() {
        this.conteudos = new HashMap<>();
        this.notaMedia = 0.0;
        this.totalAvaliacoes = 0;
    }

    @Override
    public void avaliar(double nota) {
        if (nota < 0 || nota > 5) {
            System.out.println("Nota inválida! Digite um valor entre 0 e 5.");
            return;
        }
        this.notaMedia = ((this.notaMedia * this.totalAvaliacoes) + nota) / (this.totalAvaliacoes + 1);
        this.totalAvaliacoes++;
        System.out.println("Obrigado! Sua avaliação para o catálogo foi registrada com sucesso.");
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void adicionar(Conteudo c) {
        this.conteudos.put(c.getId(), c);
    }

    public Conteudo buscarPorId(int id) throws ConteudoNaoEncontradoException {
        Conteudo c = this.conteudos.get(id);
        if (c == null) {
            throw new ConteudoNaoEncontradoException("Conteúdo com ID " + id + " não foi encontrado no catálogo.");
        }
        return c;
    }

    public Collection<Conteudo> listarTodos() {
        return this.conteudos.values();
    }

    public void remover(int id) throws ConteudoNaoEncontradoException {
        if (!this.conteudos.containsKey(id)) {
            throw new ConteudoNaoEncontradoException("Não foi possível remover: Conteúdo com ID " + id + " não encontrado.");
        }
        this.conteudos.remove(id);
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public void setTotalAvaliacoes(int totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }

}