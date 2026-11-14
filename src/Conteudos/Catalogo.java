package Conteudos;

import Exception.ConteudoNaoEncontradoException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Catalogo {

    private Map<Integer, Conteudo> conteudos;

    public Catalogo() {
        this.conteudos = new HashMap<>();
    }

    public void adicionar(Conteudo c) {

        this.conteudos.put(c.getId(), c);
        System.out.println("Conteúdo '" + c.getTitulo() + "' adicionado ao catálogo.");
    }

    public Conteudo buscarPorId(int id) throws ConteudoNaoEncontradoException {
        Conteudo c = this.conteudos.get(id);
        if (c == null){
            throw new ConteudoNaoEncontradoException("Conteúdo com ID " + id + " não encontrado.");
        }
        return c;
    }

    public List<Conteudo> buscarPorTitulo(String titulo) {
        List<Conteudo> resultados = new ArrayList<>();


        for (Conteudo c : this.conteudos.values()) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                resultados.add(c);
            }
        }
        return resultados;
    }

    public Collection<Conteudo> listarTodos() {
        return this.conteudos.values();
    }

    public void remover(int id) throws ConteudoNaoEncontradoException {
        Conteudo removido = this.conteudos.remove(id);
        if (removido != null) {
            System.out.println("Conteúdo '" + removido.getTitulo() + "' removido.");
        } else {
            throw new ConteudoNaoEncontradoException("Conteúdo com ID " + " não encontrado para remoção.");
        }
    }
}