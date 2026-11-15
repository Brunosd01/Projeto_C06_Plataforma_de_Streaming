package Service;
import Conteudos.Catalogo;
import Usuarios.Usuario;
import Exception.CadastroInvalidoException;
import Exception.LoginInvalidoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlataformaStreaming {
    private Catalogo catalogo;
    private Map<String, Usuario> usuarios;
    private GerenciadorArquivos arquivos;

    public PlataformaStreaming(){
        this.catalogo = new Catalogo();
        this.arquivos = new GerenciadorArquivos();
        this.usuarios = new HashMap<>();
    }

    public void iniciar() {
        System.out.println("Iniciando a Plataforma de Streaming...");

        // 1. Carrega o catálogo do arquivo
        this.catalogo = arquivos.carregarCatalogo();
        // 2. Carrega a lista de usuários e insere no HashMap pela chave do e-mail
        List<Usuario> listaCarregada = arquivos.carregarUsuarios(this.catalogo);
        for (Usuario u : listaCarregada) {
            this.usuarios.put(u.getEmail(), u);
        }
    }

    public Usuario login(String email, String senha) throws LoginInvalidoException {
        Usuario u = usuarios.get(email);
        if (u != null && u.autenticar(senha)) {
            return u;
        }
        throw new LoginInvalidoException("Erro: Email ou senha incorretos!");
    }

    public void cadastrarUsuario(Usuario u) throws CadastroInvalidoException {
        if(u.getNome().isBlank() || u.getEmail().isBlank() || u.getSenha().isBlank()){
            throw new CadastroInvalidoException("Erro: Todos os campos devem ser preenchidos.");
        }
        if (this.usuarios.containsKey(u.getEmail())) {
            throw new CadastroInvalidoException("Erro: Email já cadastrado.");
        }

        this.usuarios.put(u.getEmail(), u);
        this.arquivos.salvarUsuarios(this.usuarios.values());
    }

    public void encerrar() {
        System.out.println("Encerrando e salvando dados...");
        arquivos.salvarCatalogo(this.catalogo);
        arquivos.salvarUsuarios(this.usuarios.values());
    }

    public Catalogo getCatalogo() {
        return this.catalogo;
    }
}