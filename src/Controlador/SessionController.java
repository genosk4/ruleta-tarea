package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {

    private final List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioActual;

    public boolean registrarUsuario(String username, String password, String nombre) {
        if (buscarUsuario(username) != null) return false;
        Usuario u = new Usuario(username, password, nombre);
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

    // NUEVO MÉTODO: Para que la vista no acceda directamente a la lista
    public boolean existeUsuario(String username) {
        return buscarUsuario(username) != null;
    }

    private Usuario buscarUsuario(String username) {
        for (Usuario u : usuarios) if (u.getUsername().equals(username)) return u;
        return null;
    }
}





