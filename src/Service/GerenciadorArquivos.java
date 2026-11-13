package Service;

import Conteudos.Catalogo;
import Usuarios.Administrador;
import Usuarios.Assinante;
import Usuarios.Usuario;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GerenciadorArquivos {

    public void salvarCatalogo(Catalogo catalogo) {
        // Lógica com java.nio.file.Files ou BufferedWriter para gravar em catalogo.txt
    }

    public Catalogo carregarCatalogo() {
        Catalogo cat = new Catalogo();
        // Lógica para ler catalogo.txt, dar split(";") e adicionar no 'cat'
        return cat;
    }

    public void salvarUsuarios(Collection<Usuario> usuarios) {
        // Lógica para gravar em usuarios.txt
    }

    public List<Usuario> carregarUsuarios(Catalogo catalogo) {
        List<Usuario> list = new ArrayList<>();
        // Lógica para ler usuários. O parâmetro 'catalogo' serve para
        // reconectar os filmes favoritos aos usuários carregados.
        return list;
    }
}