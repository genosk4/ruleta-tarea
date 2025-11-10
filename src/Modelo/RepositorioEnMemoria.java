package Modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria {

	private List<ResultadoJuego> resultados;

	public RepositorioEnMemoria() {
		this.resultados = new ArrayList<>();
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