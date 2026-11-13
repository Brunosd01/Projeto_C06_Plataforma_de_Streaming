package Service;
import Conteudos.Catalogo;
import Usuarios.Usuario;
import java.util.Collection;

import java.util.HashMap;
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
    }

    public Usuario login(String email, String senha) {
        Usuario u = usuarios.get(email);
        if (u != null && u.autenticar(senha)) {
            return u;
        }
        return null; // Ou lançar a sua LoginInvalidoException
    }

    public void cadastrarUsuario(Usuario u) {
        this.usuarios.put(u.getEmail(), u);
    }

    public void encerrar() {
        System.out.println("Encerrando e salvando dados...");
        arquivos.salvarCatalogo(this.catalogo);
        arquivos.salvarUsuarios(this.usuarios.values());
    }
}