package Modelo;

import java.util.List;

public interface IRepositorioResultados {

	void guardarResultado(ResultadoJuego resultado);

	List<ResultadoJuego> obtenerTodos();

	List<ResultadoJuego> obtenerPorTipoApuesta(String tipoApuesta);

	int contarTotalResultados();

	int contarAciertos();


	void limpiar();

}