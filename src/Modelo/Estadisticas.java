package Modelo;

import java.util.*;

public class Estadisticas {
    private int totalJugadas;
    private int victorias;
    private double porcentajeVictorias;
    private int rachaMaxima;
    private int rachaActual;
    private TipoApuesta tipoMasJugado;
    private Usuario usuario;
    private List<Resultado> resultados;
    private Map<TipoApuesta, Integer> contadorTipos;

    public Estadisticas(Usuario usuario) {
        this.usuario = usuario;
        this.resultados = new ArrayList<>();
        this.contadorTipos = new HashMap<>();
        this.rachaMaxima = 0;
        this.rachaActual = 0;
        inicializarContadorTipos();
    }

    private void inicializarContadorTipos() {
        for (TipoApuesta tipo : TipoApuesta.values()) {
            contadorTipos.put(tipo, 0);
        }
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


        TipoApuesta tipo = resultado.getTipoApuesta();
        contadorTipos.put(tipo, contadorTipos.get(tipo) + 1);

        calcularTipoMasJugado();
    }

    private void calcularTipoMasJugado() {
        tipoMasJugado = TipoApuesta.ROJO; // Valor por defecto
        int maxCount = 0;

        for (Map.Entry<TipoApuesta, Integer> entry : contadorTipos.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                tipoMasJugado = entry.getKey();
            }
        }
    }


    public int getTotalJugadas() { return totalJugadas; }
    public int getVictorias() { return victorias; }
    public double getPorcentajeVictorias() { return porcentajeVictorias; }
    public int getRachaMaxima() { return rachaMaxima; }
    public TipoApuesta getTipoMasJugado() { return tipoMasJugado; }

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
