package Controlador;

import Modelo.Resultado;
import java.util.ArrayList;
import java.util.List;

public class ResultadoController {

    private final List<Resultado> resultados;

    public ResultadoController() {
        resultados = new ArrayList<>();
    }

    public void agregarResultado(Resultado r) {
        resultados.add(r);
    }

    public List<Resultado> getResultados() {
        return new ArrayList<>(resultados); // Devuelve copia para proteger encapsulamiento
    }

    public void limpiarResultados() {
        resultados.clear();
    }
}


