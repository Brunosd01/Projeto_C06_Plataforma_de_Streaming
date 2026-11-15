package Service;

import Conteudos.Catalogo;
import Conteudos.Conteudo;
import Conteudos.Episodio;
import Conteudos.Filme;
import Conteudos.Serie;
import Exception.ConteudoNaoEncontradoException;
import Usuarios.Administrador;
import Usuarios.Assinante;
import Usuarios.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GerenciadorArquivos {

    // Aponta para a pasta "Arquivos" dentro de "src"
    private final Path CAMINHO_CATALOGO = Paths.get("src/Arquivos/Catalogo.txt");
    private final Path CAMINHO_ASSINANTES = Paths.get("src/Arquivos/Assinante.txt");
    private final Path CAMINHO_ADMINS = Paths.get("src/Arquivos/Administrador.txt");

    private void prepararDiretorio() {
        try {
            Path diretorio = Paths.get("src/Arquivos");
            if (!Files.exists(diretorio)) {
                Files.createDirectories(diretorio);
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar a pasta de arquivos: " + e.getMessage());
        }
    }

    // --- CATÁLOGO ---

    // --- SALVAR CATÁLOGO ---
    public void salvarCatalogo(Catalogo catalogo) {
        prepararDiretorio();
        List<String> linhas = new ArrayList<>();

        // 1. Salva a avaliação geral do catálogo na primeira linha
        linhas.add("CATALOGO_INFO;" + catalogo.getNotaMedia() + ";" + catalogo.getTotalAvaliacoes());

        // 2. Salva todos os filmes e séries juntamente com suas avaliações
        for (Conteudo c : catalogo.listarTodos()) {
            if (c instanceof Filme) {
                Filme f = (Filme) c;
                String linha = "Filme;" + f.getTitulo() + ";" + f.getGenero() + ";"
                        + f.getAnoLancamento() + ";" + f.getDiretor() + ";" + f.getDuracaoMinutos()
                        + ";" + f.getNotaMedia() + ";" + f.getTotalAvaliacoes();
                linhas.add(linha);
            } else if (c instanceof Serie) {
                Serie s = (Serie) c;

                // Monta a string contendo todos os episódios (Titulo,Duracao|Titulo,Duracao)
                StringBuilder epsStr = new StringBuilder();
                List<Episodio> episodios = s.getEpisodios();
                for (int i = 0; i < episodios.size(); i++) {
                    Episodio ep = episodios.get(i);
                    epsStr.append(ep.getTituloEpisodio()).append(",").append(ep.getDuracaoMinutos());
                    if (i < episodios.size() - 1) {
                        epsStr.append("|");
                    }
                }

                String listaEpisodiosFinal = epsStr.length() > 0 ? epsStr.toString() : "SEM_EPISODIOS";

                String linha = "Serie;" + s.getTitulo() + ";" + s.getGenero() + ";"
                        + s.getAnoLancamento() + ";" + s.getDiretor() + ";"
                        + s.getTemporadas() + ";" + listaEpisodiosFinal
                        + ";" + s.getNotaMedia() + ";" + s.getTotalAvaliacoes();
                linhas.add(linha);
            }
        }

        try {
            Files.write(CAMINHO_CATALOGO, linhas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o catálogo: " + e.getMessage());
        }
    }

    // --- CARREGAR CATÁLOGO ---
    public Catalogo carregarCatalogo() {
        Catalogo cat = new Catalogo();

        if (!Files.exists(CAMINHO_CATALOGO)) {
            return cat;
        }

        try {
            List<String> linhas = Files.readAllLines(CAMINHO_CATALOGO);

            for (String linha : linhas) {
                if (linha.isBlank()) continue;
                String[] dados = linha.split(";");

                String tipo = dados[0].trim();

                // Restaura as avaliações do Catálogo
                if (tipo.equalsIgnoreCase("CATALOGO_INFO")) {
                    if (dados.length >= 3) {
                        double nota = Double.parseDouble(dados[1].trim());
                        int total = Integer.parseInt(dados[2].trim());
                        cat.setNotaMedia(nota);
                        cat.setTotalAvaliacoes(total);
                    }
                    continue;
                }

                String titulo = dados[1].trim();
                String genero = dados[2].trim();
                int ano = Integer.parseInt(dados[3].trim());
                String diretor = dados[4].trim();

                if (tipo.equalsIgnoreCase("Filme")) {
                    int duracao = Integer.parseInt(dados[5].trim());
                    Filme f = new Filme(titulo, genero, ano, diretor, duracao);

                    // Restaura a avaliação do filme se estiver salva no arquivo
                    if (dados.length >= 8) {
                        double nota = Double.parseDouble(dados[6].trim());
                        int total = Integer.parseInt(dados[7].trim());
                        f.setNotaMedia(nota);
                        f.setTotalAvaliacoes(total);
                    }

                    cat.adicionar(f);
                } else if (tipo.equalsIgnoreCase("Serie")) {
                    int temporadas = Integer.parseInt(dados[5].trim());
                    String episodiosStr = dados[6].trim();

                    // Instancia a Série vazia inicialmente
                    Serie s = new Serie(titulo, genero, ano, diretor, temporadas, 0, 0);

                    // Reconstrução dos episódios
                    if (!episodiosStr.equalsIgnoreCase("SEM_EPISODIOS")) {
                        String[] epsArray = episodiosStr.split("\\|");
                        for (String epDado : epsArray) {
                            String[] epPartes = epDado.split(",");
                            if (epPartes.length == 2) {
                                String tituloEp = epPartes[0].trim();
                                int duracaoEp = Integer.parseInt(epPartes[1].trim());
                                s.adicionarEpisodio(tituloEp, duracaoEp);
                            }
                        }
                    }

                    // Restaura a avaliação da série se estiver salva no arquivo
                    if (dados.length >= 9) {
                        double nota = Double.parseDouble(dados[7].trim());
                        int total = Integer.parseInt(dados[8].trim());
                        s.setNotaMedia(nota);
                        s.setTotalAvaliacoes(total);
                    }

                    cat.adicionar(s);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o catálogo: " + e.getMessage());
        }

        return cat;
    }

    // --- USUÁRIOS ---

    public void salvarUsuarios(Collection<Usuario> usuarios) {
        prepararDiretorio();

        List<String> linhasAssinantes = new ArrayList<>();
        List<String> linhasAdmins = new ArrayList<>();

        for (Usuario u : usuarios) {
            if (u instanceof Assinante) {
                Assinante ass = (Assinante) u;

                // Converte a lista de favoritos (minhaLista) em IDs separados por vírgula
                StringBuilder idsStr = new StringBuilder();
                List<Conteudo> lista = ass.getMinhaLista();
                for (int i = 0; i < lista.size(); i++) {
                    idsStr.append(lista.get(i).getId());
                    if (i < lista.size() - 1) {
                        idsStr.append(",");
                    }
                }
                String idsFinal = idsStr.length() > 0 ? idsStr.toString() : "SEM_LISTA";

                String linha = ass.getNome() + ";" + ass.getEmail() + ";" + ass.getSenha() + ";" + idsFinal;
                linhasAssinantes.add(linha);
            } else if (u instanceof Administrador) {
                String linha = u.getNome() + ";" + u.getEmail() + ";" + u.getSenha();
                linhasAdmins.add(linha);
            }
        }

        try {
            Files.write(CAMINHO_ASSINANTES, linhasAssinantes);
            Files.write(CAMINHO_ADMINS, linhasAdmins);
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public List<Usuario> carregarUsuarios(Catalogo catalogo) {
        List<Usuario> list = new ArrayList<>();

        // 1. Carrega Assinantes
        if (Files.exists(CAMINHO_ASSINANTES)) {
            try {
                List<String> linhas = Files.readAllLines(CAMINHO_ASSINANTES);
                for (String linha : linhas) {
                    if (linha.isBlank()) continue;
                    String[] dados = linha.split(";");
                    if (dados.length >= 3) {
                        Assinante ass = new Assinante(dados[0].trim(), dados[1].trim(), dados[2].trim());

                        // Reconstrói a lista de favoritos do assinante a partir dos IDs salvos
                        if (dados.length >= 4) {
                            String idsStr = dados[3].trim();
                            if (!idsStr.equalsIgnoreCase("SEM_LISTA")) {
                                String[] idsArray = idsStr.split(",");
                                for (String idTxt : idsArray) {
                                    try {
                                        int id = Integer.parseInt(idTxt.trim());
                                        Conteudo conteudo = catalogo.buscarPorId(id);
                                        if (conteudo != null) {
                                            ass.adicionarNaLista(conteudo);
                                        }
                                    } catch (NumberFormatException e) {
                                        // Ignora falhas de formatação numérica no arquivo
                                    } catch (ConteudoNaoEncontradoException e) {
                                        // Ignora caso o conteúdo tenha sido removido do catálogo
                                    }
                                }
                            }
                        }
                        list.add(ass);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar assinantes: " + e.getMessage());
            }
        }

        // 2. Carrega Administradores
        if (Files.exists(CAMINHO_ADMINS)) {
            try {
                List<String> linhas = Files.readAllLines(CAMINHO_ADMINS);
                for (String linha : linhas) {
                    if (linha.isBlank()) continue;
                    String[] dados = linha.split(";");
                    if (dados.length >= 3) {
                        Administrador admin = new Administrador(dados[0].trim(), dados[1].trim(), dados[2].trim());
                        list.add(admin);
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar administradores: " + e.getMessage());
            }
        }

        return list;
    }
}