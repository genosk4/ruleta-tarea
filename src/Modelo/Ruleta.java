package Modelo;

import java.util.Random;

public class Ruleta {
    private int saldo;
    private static final int MAX_HISTORIAL = 100;

    private final IRepositorioResultados repositorio;
    private static final Random rng = new Random();

    public Ruleta(int saldoInicial, IRepositorioResultados repositorio) {
        this.saldo = Math.max(saldoInicial, 0);
        this.repositorio = repositorio;
    }

    public Ruleta(IRepositorioResultados repositorio) {
        this(0, repositorio);
    }

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public ResultadoJuego jugar(ApuestaBase apuesta) {
        if (apuesta.getMonto() > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        int numeroGanador = girarRuleta();
        boolean acierto = apuesta.acierta(numeroGanador);

        if (acierto) {
            saldo += apuesta.getMonto();
        } else {
            saldo -= apuesta.getMonto();
        }

        ResultadoJuego resultado = new ResultadoJuego(numeroGanador, acierto, apuesta);

        repositorio.guardarResultado(resultado);

        return resultado;
    }

    public static String getEstadisticas(IRepositorioResultados repositorio) {
        int totalJugadas = repositorio.contarTotalResultados();
        int totalAciertos = repositorio.contarAciertos();
        int totalApostado = calcularTotalApostado(repositorio);
        int ganancia = (totalAciertos * 2) - totalApostado;

        StringBuilder sb = new StringBuilder();
        sb.append("----- Estadisticas -----\n");
        sb.append("Cantidad de rondas: ").append(totalJugadas).append("\n");
        sb.append("Total apostado: $").append(totalApostado).append("\n");
        sb.append("Total aciertos: ").append(totalAciertos).append("\n");
        if (totalJugadas > 0) {
            double porcentaje = (totalAciertos * 100.0) / totalJugadas;
            sb.append("% de acierto: ").append(String.format("%.2f", porcentaje)).append("%\n");
        }
        sb.append("Ganancia/Perdida neta: $").append(ganancia).append("\n");
        return sb.toString();
    }

    private static int calcularTotalApostado(IRepositorioResultados repositorio) {
        return repositorio.obtenerTodos().stream()
                .mapToInt(ResultadoJuego::getMontoApostado)
                .sum();
    }

    public int getSaldo() { return saldo; }

    public IRepositorioResultados getRepositorio() { return repositorio; }
}


