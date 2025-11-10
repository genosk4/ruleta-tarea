package Controlador;

import Modelo.Usuario;
import Modelo.IRepositorioResultados;
import Modelo.RepositorioEnMemoria;
import Modelo.RepositorioArchivo;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private List<Usuario> usuarios;
    private Usuario usuarioActual;
    private final IRepositorioResultados repositorio;

    public SessionController(boolean usarArchivo) {
        if (usarArchivo) {
            this.repositorio = new RepositorioArchivo();
        } else {
            this.repositorio = new RepositorioEnMemoria();
        }
        this.usuarios = new ArrayList<>();
        crearUsuariosEjemplo();
    }

    private void crearUsuariosEjemplo() {
        registrarUsuario("daniel", "1234", "Daniel Lincopi");
        registrarUsuario("GenosK4", "12345", "Daniel");
    }

    public boolean registrarUsuario(String username, String password, String nombre) {
        if (buscarUsuario(username) != null) return false;
        Usuario u = new Usuario(username, password, nombre, repositorio);
        usuarios.add(u);
        return true;
    }

    public boolean login(String username, String password) {
        Usuario u = buscarUsuario(username);
        if (u != null && u.validarCredenciales(username, password)) {
            usuarioActual = u;
            return true;
        }
        return false;
    }

    public void logout() {
        usuarioActual = null;
    }

    public Usuario getUsuarioActual() { return usuarioActual; }
    public IRepositorioResultados getRepositorio() { return repositorio; }

    public boolean existeUsuario(String username) {
        return buscarUsuario(username) != null;
    }

    private Usuario buscarUsuario(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
}





