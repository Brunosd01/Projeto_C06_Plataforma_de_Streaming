package Conteudos;

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

    public Conteudo buscarPorId(int id) {
        return this.conteudos.get(id);
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

    public void remover(int id) {
        Conteudo removido = this.conteudos.remove(id);
        if (removido != null) {
            System.out.println("Conteúdo '" + removido.getTitulo() + "' removido.");
        } else {
            System.out.println("Conteúdo não encontrado para remoção.");
        }
    }
}