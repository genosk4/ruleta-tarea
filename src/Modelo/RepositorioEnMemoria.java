package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioEnMemoria implements IRepositorioResultados {
    private final List<ResultadoJuego> resultados;
    private List<Usuario> usuarios;

    public RepositorioEnMemoria() {
        this.resultados = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    @Override
    public void guardarUsuarios(List<Usuario> usuarios) {
        this.usuarios = new ArrayList<>(usuarios);
        System.out.println("Usuarios guardados en memoria: " + usuarios.size());
    }

    @Override
    public List<Usuario> cargarUsuarios() {
        System.out.println("Cargando usuarios desde memoria: " + usuarios.size());
        return new ArrayList<>(usuarios);
    }

    @Override
    public void guardarEstadoCompleto(List<Usuario> usuarios) {
        guardarUsuarios(usuarios);
        System.out.println("Estado completo guardado en memoria");
    }

    @Override
    public void guardarResultado(ResultadoJuego resultado) {
        resultados.add(resultado);
    }

    @Override
    public List<ResultadoJuego> obtenerTodos() {
        return new ArrayList<>(resultados);
    }

    @Override
    public List<ResultadoJuego> obtenerPorTipoApuesta(String tipoApuesta) {
        return resultados.stream()
                .filter(r -> r.getTipoApuesta().equalsIgnoreCase(tipoApuesta))
                .collect(Collectors.toList());
    }

    @Override
    public int contarTotalResultados() {
        return resultados.size();
    }

    @Override
    public int contarAciertos() {
        return (int) resultados.stream()
                .filter(ResultadoJuego::isAcierto)
                .count();
    }

    @Override
    public void limpiar() {
        resultados.clear();
    }
}