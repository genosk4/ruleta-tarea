package Controlador;

import Modelo.Usuario;

public class SessionController {
    private Usuario usuarioActual;
    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.
                isBlank())
            throw new IllegalArgumentException("Datos requeridos");
        this.usuarioActual = new Usuario(u, p, n);
    }
    public boolean iniciarSesion(String u, String p) {
        if (usuarioActual == null) return false;
        return usuarioActual.validarCredenciales(u, p);
    }
    public boolean hayUsuario() {
        return usuarioActual != null;
    }
    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }
7
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public void cerrarSesion() {
        usuarioActual = null;
    }
}

