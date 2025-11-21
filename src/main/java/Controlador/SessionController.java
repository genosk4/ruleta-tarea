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
        this.repositorio = usarArchivo ? new RepositorioArchivo() : new RepositorioEnMemoria();

        this.usuarios = repositorio.cargarUsuarios();


          if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios, creando datos de ejemplo...");
            crearUsuariosEjemplo();
        } else {
            System.out.println("Usuarios cargados exitosamente: " + usuarios.size());
        }

    }

    private void crearUsuariosEjemplo() {
        registrarUsuario("daniel", "1234", "Daniel Lincopi");
    }

    public boolean registrarUsuario(String username, String password, String nombre) {
        if (buscarUsuario(username) != null) return false;
        Usuario u = new Usuario(username, password, nombre, repositorio);
        usuarios.add(u);
        repositorio.guardarUsuarios(usuarios);
        return true;
    }

    public void guardarEstado() {
        repositorio.guardarEstadoCompleto(usuarios);
        System.out.println("Estado del sistema guardado");
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





