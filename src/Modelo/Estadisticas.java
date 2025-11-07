package Modelo;

import java.util.*;
import java.util.stream.Collectors;

public class Estadisticas {
    private int totalJugadas;
    private int victorias;
    private double porcentajeVictorias;
    private int rachaMaxima;
    private int rachaActual;
    private String tipoMasJugado;
    private final IRepositorioResultados repositorio;
    private List<Resultado> resultados;
    private Map<String, Integer> contadorTipos;

    public Estadisticas(Usuario usuario, IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
        this.resultados = new ArrayList<>();
        this.contadorTipos = new HashMap<>();
        this.rachaMaxima = 0;
        this.rachaActual = 0;
        inicializarContadorTipos();
    }

    private void inicializarContadorTipos() {
        contadorTipos.put("ROJO", 0);
        contadorTipos.put("NEGRO", 0);
        contadorTipos.put("PAR", 0);
        contadorTipos.put("IMPAR", 0);
    }

    public void actualizarEstadisticas(Resultado resultado) {
        resultados.add(resultado);
        totalJugadas++;

        if (resultado.isAcierto()) {
            victorias++;
            rachaActual++;
            rachaMaxima = Math.max(rachaMaxima, rachaActual);
        } else {
            rachaActual = 0;
        }

        porcentajeVictorias = totalJugadas > 0 ? (victorias * 100.0) / totalJugadas : 0;

        String tipo = resultado.getTipoApuesta().name();
        contadorTipos.put(tipo, contadorTipos.get(tipo) + 1);

        calcularTipoMasJugado();
    }

    public void actualizarDesdeRepositorio() {
        List<ResultadoJuego> resultadosJuego = repositorio.obtenerTodos();
        totalJugadas = resultadosJuego.size();
        victorias = repositorio.contarAciertos();
        porcentajeVictorias = totalJugadas > 0 ? (victorias * 100.0) / totalJugadas : 0;

        calcularTipoMasJugadoDesdeRepositorio();
    }

    private void calcularTipoMasJugado() {
        tipoMasJugado = "ROJO";
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : contadorTipos.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                tipoMasJugado = entry.getKey();
            }
        }
    }

    private void calcularTipoMasJugadoDesdeRepositorio() {
        Map<String, Long> conteo = repositorio.obtenerTodos().stream()
                .collect(Collectors.groupingBy(
                        ResultadoJuego::getTipoApuesta,
                        Collectors.counting()
                ));

        tipoMasJugado = conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("ROJO");
    }

    public int getTotalJugadas() { return totalJugadas; }
    public int getVictorias() { return victorias; }
    public double getPorcentajeVictorias() { return porcentajeVictorias; }
    public int getRachaMaxima() { return rachaMaxima; }
    public String getTipoMasJugado() { return tipoMasJugado; }

    public void reiniciarEstadisticas() {
        totalJugadas = 0;
        victorias = 0;
        porcentajeVictorias = 0;
        rachaMaxima = 0;
        rachaActual = 0;
        resultados.clear();
        inicializarContadorTipos();
    }
}
