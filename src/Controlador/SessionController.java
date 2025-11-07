package Controlador;

import Modelo.Usuario;
import Modelo.GestorPersistencia;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private List<Usuario> usuarios;
    private Usuario usuarioActual;
    private final GestorPersistencia gestorPersistencia;

    public SessionController() {
        this.gestorPersistencia = new GestorPersistencia();
        this.usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        this.usuarios = gestorPersistencia.cargarEstadoCompleto();

        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios, creando datos de ejemplo...");
            crearUsuariosEjemplo();
        }
    }

    private void crearUsuariosEjemplo() {
        registrarUsuario("daniel", "1234", "Daniel Lincopi");
        registrarUsuario("GenosK4", "12345", "Daniel");
    }

    public boolean registrarUsuario(String username, String password, String nombre) {
        if (buscarUsuario(username) != null) return false;
        Usuario u = new Usuario(username, password, nombre);
        usuarios.add(u);

        gestorPersistencia.guardarEstadoCompleto(usuarios);
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
        if (usuarioActual != null) {
            gestorPersistencia.guardarEstadoCompleto(usuarios);
        }
        usuarioActual = null;
    }

    public Usuario getUsuarioActual() { return usuarioActual; }

    public boolean existeUsuario(String username) {
        return buscarUsuario(username) != null;
    }

    private Usuario buscarUsuario(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public void guardarEstado() {
        gestorPersistencia.guardarEstadoCompleto(usuarios);
    }
}





